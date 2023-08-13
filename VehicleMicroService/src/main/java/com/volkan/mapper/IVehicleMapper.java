package com.volkan.mapper;

import com.volkan.dto.request.CreateVehicleRequestDto;
import com.volkan.dto.request.UpdateVehicleRequestDto;
import com.volkan.dto.response.VehicleResponseDto;
import com.volkan.repository.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IVehicleMapper {
    IVehicleMapper INSTANCE = Mappers.getMapper(IVehicleMapper.class);
    Vehicle toVehicle(final CreateVehicleRequestDto dto);
    Vehicle toVehicle(final UpdateVehicleRequestDto dto);
    VehicleResponseDto toVehicleResponseDto(final Vehicle vehicle);
//    @Mapping(source = "id",target = "userId")
//    RegisterModel toRegisterModel(final User user);
//    RegisterMailModel toRegisterMailModel(final User user);
}
