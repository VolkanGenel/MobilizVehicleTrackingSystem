package com.volkan.service;

import com.volkan.dto.request.CreateSectorRequestDto;
import com.volkan.dto.request.UpdateSectorRequestDto;
import com.volkan.dto.response.SectorResponseDto;
import com.volkan.exception.DistrictAndAuthorizationManagerException;
import com.volkan.exception.EErrorType;
import com.volkan.mapper.ISectorMapper;
import com.volkan.repository.ISectorRepository;
import com.volkan.repository.entity.Sector;
import com.volkan.utility.JwtTokenManager;
import com.volkan.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectorService extends ServiceManager<Sector,Long> {
    private final ISectorRepository sectorRepository;
    private final JwtTokenManager tokenManager;


    public SectorService(ISectorRepository sectorRepository, JwtTokenManager tokenManager) {
        super(sectorRepository);
        this.sectorRepository = sectorRepository;
        this.tokenManager = tokenManager;
    }


    public SectorResponseDto createSector(CreateSectorRequestDto dto) {
        if (sectorRepository.isName(dto.getName()))
            throw new DistrictAndAuthorizationManagerException(EErrorType.SECTOR_DUPLICATE);
        Sector sector= ISectorMapper.INSTANCE.toSector(dto);
        save(sector);
        SectorResponseDto responseDto = ISectorMapper.INSTANCE.toSectorResponseDto(sector);
        return responseDto;
    }
    public SectorResponseDto updateSector(Long sectorId, UpdateSectorRequestDto dto) {
        Optional<Sector> sector = findById(sectorId);
        if (sector.isEmpty())
            throw new DistrictAndAuthorizationManagerException(EErrorType.SECTOR_NOT_FOUND);
        Sector updateSector = ISectorMapper.INSTANCE.toSector(dto);
        updateSector.setSectorId(sector.get().getSectorId());
        update(updateSector);
        SectorResponseDto responseDto = ISectorMapper.INSTANCE.toSectorResponseDto(updateSector);
        return responseDto;
    }

}
