package com.volkan.mapper;

import com.volkan.dto.request.CreateUserRequestDto;
import com.volkan.dto.request.UserRegisterRequestDto;
import com.volkan.dto.response.UserRegisterResponseDto;
import com.volkan.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);
    User toUser(final UserRegisterRequestDto dto);
    User toUser(final CreateUserRequestDto dto);
}
