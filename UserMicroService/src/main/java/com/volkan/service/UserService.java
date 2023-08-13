package com.volkan.service;

import com.volkan.dto.request.CreateUserRequestDto;
import com.volkan.dto.request.DoLoginRequestDto;
import com.volkan.dto.request.UpdatePasswordRequestDto;
import com.volkan.dto.request.UserRegisterRequestDto;
import com.volkan.exception.EErrorType;
import com.volkan.exception.UserManagerException;
import com.volkan.mapper.IUserMapper;
import com.volkan.rabbitmq.model.AuthorizationUserModel;
import com.volkan.repository.IUserRepository;
import com.volkan.repository.entity.User;
import com.volkan.repository.enums.ERole;
import com.volkan.repository.enums.EStatus;
import com.volkan.utility.JwtTokenManager;
import com.volkan.utility.ServiceManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends ServiceManager<User,Long> {
    private final IUserRepository userRepository;
    private final JwtTokenManager tokenManager;


    public UserService(IUserRepository userRepository, JwtTokenManager tokenManager) {
        super(userRepository);
        this.userRepository = userRepository;
        this.tokenManager = tokenManager;
    }


    public Boolean register(UserRegisterRequestDto dto) {
        if (userRepository.isEmail(dto.getEmail()))
            throw new UserManagerException(EErrorType.USER_DUPLICATE);
        User user = IUserMapper.INSTANCE.toUser(dto);
        save(user);
        return true;
    }
    public Boolean createStandardUser(CreateUserRequestDto dto) {
        if (userRepository.isEmail(dto.getEmail()))
            throw new UserManagerException(EErrorType.USER_DUPLICATE);
        User user = IUserMapper.INSTANCE.toUser(dto);
        save(user);
        return true;
    }
    public String doLogin(DoLoginRequestDto dto) {
        Optional<User> user = userRepository.findOptionalByEmailIgnoreCaseAndPassword(dto.getEmail(),dto.getPassword());
        if (user.isEmpty())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        List<Long> allowedList = user.get().getAuthorizationIds().stream().toList();
        ERole role = user.get().getRole();
        Optional<String> token = tokenManager.createToken(user.get().getUserId(),allowedList,role);
        if (token.isEmpty())
            throw new UserManagerException(EErrorType.TOKEN_CREATION_ERROR);
        return token.get();
    }


    public Boolean deleteUser(Long id) {
        Optional<User> user = findById(id);
        if (user.isEmpty())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        user.get().setStatus(EStatus.DELETED);
        update(user.get());
        return true;
    }

    public Boolean updatePassword(String token, UpdatePasswordRequestDto dto) {
        if (!dto.getPassword().equals(dto.getRePassword()))
            throw new UserManagerException(EErrorType.PASSWORD_UNMATCH_ERROR);
        Optional<Long> userId = tokenManager.getIdFromToken(token);
        if (userId.isEmpty())
            throw new UserManagerException(EErrorType.INVALID_TOKEN);
        Optional<User> user = findById(userId.get());
        if (user.isEmpty())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        user.get().setPassword(dto.getPassword());
        update(user.get());
        return true;
    }
    public Optional<String> getTokenFindById(Long userId) {
        Optional<User> user = findById(userId);
        if (user.isEmpty())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        List<Long> allowedList = user.get().getAuthorizationIds().stream().toList();
        ERole role = user.get().getRole();
        Optional<String> token = tokenManager.createToken(userId,allowedList,role);
        if (token.isEmpty())
            throw new UserManagerException(EErrorType.TOKEN_CREATION_ERROR);
        return token;
    }

    public ResponseEntity<Void> authorizeUser(AuthorizationUserModel model) {
        System.out.println(model.getUserId());
        model.getAuthorizationIds().forEach(System.out::println);
        System.out.println("*********12312312412");
        Optional<User> user = findById(model.getUserId());
        System.out.println(user.get().getName());
        if (user.isEmpty())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        try {
            user.get().setAuthorizationIds(model.getAuthorizationIds());
            update(user.get());
        } catch (Exception exception) {
            System.out.println("HATANIN KRALI");
        }
        return ResponseEntity.ok().build();
    }
}
