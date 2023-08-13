package com.volkan.mapper;

import com.volkan.dto.request.CreateAuthorizationRequestDto;
import com.volkan.dto.request.UpdateAuthorizationRequestDto;
import com.volkan.dto.response.AuthorizationResponseDto;
import com.volkan.dto.response.AuthorizationWithTokenResponseDto;
import com.volkan.repository.entity.Authorization;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IAuthorizationMapper {
    IAuthorizationMapper INSTANCE = Mappers.getMapper(IAuthorizationMapper.class);
    AuthorizationWithTokenResponseDto toAuthorizationWithTokenResponseDto(final Authorization authorization);
}
