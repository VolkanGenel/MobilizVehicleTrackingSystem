package com.volkan.controller;

import com.volkan.dto.request.AuthorizeRequestDto;
import com.volkan.dto.request.CreateAuthorizationRequestDto;
import com.volkan.dto.request.UpdateAuthorizationRequestDto;
import com.volkan.dto.response.AuthorizationResponseDto;
import com.volkan.dto.response.AuthorizationWithTokenResponseDto;
import com.volkan.mapper.IAuthorizationMapper;
import com.volkan.repository.entity.Authorization;
import com.volkan.service.AuthorizationService;
import com.volkan.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.volkan.constants.ApiUrls.*;

@RestController
@RequestMapping(AUTHORIZATION)
@RequiredArgsConstructor
public class AuthorizationController {

   private final AuthorizationService authorizationService;
    @PostMapping(CREATE)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<AuthorizationResponseDto> createAuthorization(@RequestBody @Valid CreateAuthorizationRequestDto createDto) {
        AuthorizationResponseDto responseDto = authorizationService.createAuthorization(createDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(FIND_ALL)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<List<Authorization>> getAllAuthorizations() {
        List<Authorization> authorizations = authorizationService.findAll();
        return ResponseEntity.ok(authorizations);
    }

    @GetMapping(FIND_BY_ID+"/{authorizationId}")
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<?> getAuthorizationById(@PathVariable Long authorizationId) {
        Optional<Authorization> authorization = authorizationService.findById(authorizationId);
        AuthorizationWithTokenResponseDto dto = IAuthorizationMapper.INSTANCE.toAuthorizationWithTokenResponseDto(authorization.get());
        return ResponseEntity.ok(authorization.isEmpty()? "Yetki bulunamadı":dto);
    }

    @PutMapping(UPDATE)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<AuthorizationResponseDto> updateAuthorization(@RequestBody @Valid UpdateAuthorizationRequestDto updateDto) {
        AuthorizationResponseDto updatedAuthorization = authorizationService.updateAuthorization(updateDto);
        return ResponseEntity.ok(updatedAuthorization);
    }

    @DeleteMapping(DELETE_BY_ID+"/{authorizationId}")
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<Void> deleteAuthorization(@PathVariable Long authorizationId) {
        authorizationService.deleteById(authorizationId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(GET_TOKEN_FIND_BY_ID)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<String> getTokenFindById(@RequestParam Long authorizationId) {
       return ResponseEntity.ok(authorizationService.getTokenFindById(authorizationId).get());
    }
    @PostMapping(AUTHORIZE_VEHICLE)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<Void> authorizeVehicle(@RequestBody AuthorizeRequestDto dto) {
        authorizationService.authorizeVehicle(dto);
        return ResponseEntity.noContent().build();
    }
    @PostMapping(AUTHORIZE_USER)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<Void> authorizeUser(@RequestBody AuthorizeRequestDto dto) {
        authorizationService.authorizeUser(dto);
        return ResponseEntity.noContent().build();
    }

}
