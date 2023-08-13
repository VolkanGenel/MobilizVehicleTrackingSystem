package com.volkan.controller;

import com.volkan.dto.request.CreateUserRequestDto;
import com.volkan.dto.request.DoLoginRequestDto;
import com.volkan.dto.request.UpdatePasswordRequestDto;
import com.volkan.dto.request.UserRegisterRequestDto;
import com.volkan.repository.entity.User;
import com.volkan.service.UserService;
import com.volkan.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.volkan.constants.ApiUrls.*;
import static com.volkan.constants.ApiUrls.GET_TOKEN_FIND_BY_ID;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenManager tokenManager;

    @PostMapping(REGISTER)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<Boolean> register(@RequestBody @Valid UserRegisterRequestDto dto) {
        return ResponseEntity.ok(userService.register(dto));
    }
    @PostMapping(LOGIN)
    public ResponseEntity<String> doLogin(@RequestBody DoLoginRequestDto dto) {
        return ResponseEntity.ok(userService.doLogin(dto));
    }

    @PostMapping(CREATE_STANDARD_USER)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<Boolean> createStandardUser(@RequestBody @Valid CreateUserRequestDto dto) {
        return ResponseEntity.ok(userService.createStandardUser(dto));
    }

    @GetMapping(FIND_ALL)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PutMapping(UPDATE_PASSWORD)
    public ResponseEntity<Boolean> updatePassword(@RequestHeader String token,
                                                         @RequestBody @Valid UpdatePasswordRequestDto dto) {
        return ResponseEntity.ok(userService.updatePassword(token,dto));
    }

    @DeleteMapping(DELETE_BY_ID)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<Boolean> delete (Long userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @GetMapping(GET_TOKEN_FIND_BY_ID)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<String> getTokenFindById(@RequestParam Long userId) {
        return ResponseEntity.ok(userService.getTokenFindById(userId).get());
    }
}
