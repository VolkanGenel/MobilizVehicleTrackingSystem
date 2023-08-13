package com.volkan.service;

import com.volkan.dto.request.UpdateZoneRequestDto;
import com.volkan.dto.response.ZoneResponseDto;
import com.volkan.exception.DistrictAndAuthorizationManagerException;
import com.volkan.exception.EErrorType;
import com.volkan.mapper.IZoneMapper;
import com.volkan.repository.IZoneRepository;
import com.volkan.repository.entity.Zone;
import com.volkan.utility.JwtTokenManager;
import com.volkan.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ZoneService extends ServiceManager<Zone,Long> {
    private final IZoneRepository zoneRepository;
    private final JwtTokenManager tokenManager;


    public ZoneService(IZoneRepository zoneRepository, JwtTokenManager tokenManager) {
        super(zoneRepository);
        this.zoneRepository = zoneRepository;
        this.tokenManager = tokenManager;
    }


    public ZoneResponseDto createZone(String name)  {
        if (zoneRepository.isName(name))
            throw new DistrictAndAuthorizationManagerException(EErrorType.ZONE_DUPLICATE);
        Zone zone = Zone.builder().name(name).build();
        save(zone);
        ZoneResponseDto responseDto = IZoneMapper.INSTANCE.toZoneResponseDto(zone);
        return responseDto;
    }
    public ZoneResponseDto updateZone(Long zoneId, UpdateZoneRequestDto dto) {

        Optional<Zone> zone = findById(zoneId);
        if (zone.isEmpty())
            throw new DistrictAndAuthorizationManagerException(EErrorType.ZONE_NOT_FOUND);
        Zone updateZone = IZoneMapper.INSTANCE.toZone(dto);
        updateZone.setZoneId(zone.get().getZoneId());
        update(updateZone);
        ZoneResponseDto responseDto = IZoneMapper.INSTANCE.toZoneResponseDto(updateZone);
        return responseDto;
    }

}
