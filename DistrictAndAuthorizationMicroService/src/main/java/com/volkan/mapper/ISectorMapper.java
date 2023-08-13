package com.volkan.mapper;

import com.volkan.dto.request.CreateSectorRequestDto;
import com.volkan.dto.request.UpdateSectorRequestDto;
import com.volkan.dto.response.SectorResponseDto;
import com.volkan.repository.entity.Sector;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ISectorMapper {
    ISectorMapper INSTANCE = Mappers.getMapper(ISectorMapper.class);
    Sector toSector(final CreateSectorRequestDto dto);
    Sector toSector(final UpdateSectorRequestDto dto);
    SectorResponseDto toSectorResponseDto(final Sector sector);
}
