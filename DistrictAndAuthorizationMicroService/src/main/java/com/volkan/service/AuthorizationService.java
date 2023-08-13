package com.volkan.service;

import com.volkan.dto.request.AuthorizeRequestDto;
import com.volkan.dto.request.CreateAuthorizationRequestDto;
import com.volkan.dto.request.UpdateAuthorizationRequestDto;
import com.volkan.dto.response.AuthorizationResponseDto;
import com.volkan.exception.DistrictAndAuthorizationManagerException;
import com.volkan.exception.EErrorType;
import com.volkan.rabbitmq.model.AuthorizationUserModel;
import com.volkan.rabbitmq.model.AuthorizationVehicleModel;
import com.volkan.rabbitmq.producer.AuthorizationUserProducer;
import com.volkan.rabbitmq.producer.AuthorizationVehicleProducer;
import com.volkan.repository.IAuthorizationRepository;
import com.volkan.repository.entity.Authorization;
import com.volkan.utility.JwtTokenManager;
import com.volkan.utility.ServiceManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorizationService extends ServiceManager<Authorization,Long> {
    private final IAuthorizationRepository authorizationRepository;
    private final ZoneService zoneService;
    private final SectorService sectorService;
    private final JwtTokenManager tokenManager;
    private final AuthorizationUserProducer authorizationUserProducer;
    private final AuthorizationVehicleProducer authorizationVehicleProducer;


    public AuthorizationService(IAuthorizationRepository authorizationRepository, ZoneService zoneService, SectorService sectorService, JwtTokenManager tokenManager, AuthorizationUserProducer authorizationUserProducer, AuthorizationVehicleProducer authorizationVehicleProducer) {
        super(authorizationRepository);
        this.authorizationRepository = authorizationRepository;
        this.zoneService = zoneService;
        this.sectorService = sectorService;
        this.tokenManager = tokenManager;
        this.authorizationUserProducer = authorizationUserProducer;
        this.authorizationVehicleProducer = authorizationVehicleProducer;
    }


    public AuthorizationResponseDto createAuthorization(CreateAuthorizationRequestDto dto) {
        if(dto.getZoneId()==null)
            dto.setZoneId(0L);
        if(dto.getSectorId()==null)
            dto.setSectorId(0L);
        if(dto.getZoneId()>0 && zoneService.findById(dto.getZoneId()).isEmpty())
            throw new DistrictAndAuthorizationManagerException(EErrorType.ZONE_NOT_FOUND_CREATE_ONE);
        if(dto.getSectorId()>0 && sectorService.findById(dto.getSectorId()).isEmpty())
            throw new DistrictAndAuthorizationManagerException(EErrorType.SECTOR_NOT_FOUND_CREATE_ONE);
        if (authorizationRepository.existsByZoneIdAndSectorIdAndECity(dto.getZoneId(),dto.getSectorId(),dto.getECity()))
            throw new DistrictAndAuthorizationManagerException(EErrorType.AUTHORIZATION_DUPLICATE);
        Authorization authorization = Authorization.builder().sectorId(dto.getSectorId()).eCity(dto.getECity()).zoneId(dto.getZoneId()).build();

        save(authorization);
        setAllowance();
        AuthorizationResponseDto responseDto = AuthorizationResponseDto.builder()
                .sectorId(authorization.getSectorId())
                .authorizationId(authorization.getAuthorizationId())
                .zoneId(authorization.getZoneId())
                .eCity(authorization.getECity())
                .build();
        return responseDto;
    }
    public AuthorizationResponseDto updateAuthorization(UpdateAuthorizationRequestDto dto) {
        Optional<Authorization> authorization = findById(dto.getAuthorizationId());
        if (authorization.isEmpty())
            throw new DistrictAndAuthorizationManagerException(EErrorType.AUTHORIZATION_NOT_FOUND);
        if(dto.getZoneId()==null)
            dto.setZoneId(0L);
        if(dto.getSectorId()==null)
            dto.setSectorId(0L);
        if(dto.getZoneId()>0 && zoneService.findById(dto.getZoneId()).isEmpty())
            throw new DistrictAndAuthorizationManagerException(EErrorType.ZONE_NOT_FOUND_CREATE_ONE);
        if(dto.getSectorId()>0 && sectorService.findById(dto.getSectorId()).isEmpty())
            throw new DistrictAndAuthorizationManagerException(EErrorType.SECTOR_NOT_FOUND_CREATE_ONE);
        if (authorizationRepository.existsByZoneIdAndSectorIdAndECity(dto.getZoneId(),dto.getSectorId(),dto.getECity()))
            throw new DistrictAndAuthorizationManagerException(EErrorType.AUTHORIZATION_DUPLICATE);
        authorization.get().setSectorId(dto.getSectorId());
        authorization.get().setZoneId(dto.getZoneId());
        authorization.get().setECity(dto.getECity());
        update(authorization.get());
        AuthorizationResponseDto responseDto = AuthorizationResponseDto.builder()
                .sectorId(authorization.get().getSectorId())
                .authorizationId(authorization.get().getAuthorizationId())
                .zoneId(authorization.get().getZoneId())
                .eCity(authorization.get().getECity())
                .build();
        return responseDto;
    }
    public Optional<String> getTokenFindById(Long authorizationId) {
        Optional<Authorization> authorization = findById(authorizationId);
        if (authorization.isEmpty())
            throw new DistrictAndAuthorizationManagerException(EErrorType.AUTHORIZATION_NOT_FOUND);
        Optional<String> token = tokenManager.createToken(authorizationId);
        if (token.isEmpty())
            throw new DistrictAndAuthorizationManagerException(EErrorType.TOKEN_CREATION_ERROR);
        return token;
    }
    @Transactional
    public ResponseEntity<Void> authorizeVehicle(AuthorizeRequestDto dto) {
        Optional<Authorization> authorization = findById(dto.getAuthorizeId());
        if (authorization.isEmpty())
            throw new DistrictAndAuthorizationManagerException(EErrorType.AUTHORIZATION_NOT_FOUND);
        Optional<Long> vehicleId = tokenManager.getIdFromToken(dto.getToken());
        try {
            update(authorization.get());
            authorizationVehicleProducer.sendAuthorization(AuthorizationVehicleModel.builder()
                    .vehicleId(vehicleId.get())
                    .authorizationId(dto.getAuthorizeId())
                    .build());
        } catch (Exception exception) {
            throw new DistrictAndAuthorizationManagerException(EErrorType.AUTHORIZATION_NOT_CREATED);
        }
        return ResponseEntity.ok().build();
    }
    @Transactional
    public ResponseEntity<Void> authorizeUser(AuthorizeRequestDto dto) {
        Optional<Authorization> authorization = findById(dto.getAuthorizeId());
        if (authorization.isEmpty())
            throw new DistrictAndAuthorizationManagerException(EErrorType.AUTHORIZATION_NOT_FOUND);
        Optional<Long> userId = tokenManager.getIdFromToken(dto.getToken());
        AuthorizationUserModel model = AuthorizationUserModel.builder()
                .userId(userId.get())
                .build();
        model.getAuthorizationIds().addAll(authorization.get().getAllowedList());
        model.getAuthorizationIds().forEach(System.out::println);
        model.setUserId(userId.get());
        try {
            update(authorization.get());
            authorizationUserProducer.sendAuthorization(model);
        } catch (Exception exception) {
            throw new DistrictAndAuthorizationManagerException(EErrorType.AUTHORIZATION_NOT_CREATED);
        }
        return ResponseEntity.ok().build();
    }

    public void setAllowance() {
        List<Authorization> authorizations = findAll();
        authorizations.forEach(x->{
            List<Authorization> newAuthorizations = findAll();
            newAuthorizations.forEach(y->{
                if (x.getZoneId()==0 && x.getSectorId()==0 && x.getECity().equals(y.getECity())) {
                    x.getAllowedList().add(y.getAuthorizationId());
                } else if (x.getZoneId()==0 && x.getECity().equals(y.getECity()) && x.getSectorId()==y.getSectorId()) {
                    x.getAllowedList().add(y.getAuthorizationId());
                } else if (x.getSectorId()==0 && x.getECity().equals(y.getECity()) && x.getZoneId()==y.getZoneId()) {
                    x.getAllowedList().add(y.getAuthorizationId());
                } else {
                    x.getAllowedList().add(x.getAuthorizationId());
                }
            });
            update(x);
        });
    }
}
