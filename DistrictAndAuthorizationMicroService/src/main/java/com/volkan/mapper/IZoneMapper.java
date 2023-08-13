package com.volkan.mapper;

import com.volkan.dto.request.UpdateZoneRequestDto;
import com.volkan.dto.response.ZoneResponseDto;
import com.volkan.repository.entity.Zone;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IZoneMapper {
    IZoneMapper INSTANCE = Mappers.getMapper(IZoneMapper.class);
    Zone toZone(final UpdateZoneRequestDto dto);
    ZoneResponseDto toZoneResponseDto(final Zone zone);
}
