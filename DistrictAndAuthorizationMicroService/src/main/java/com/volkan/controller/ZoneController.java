package com.volkan.controller;

import com.volkan.dto.request.UpdateZoneRequestDto;
import com.volkan.dto.response.ZoneResponseDto;
import com.volkan.exception.DistrictAndAuthorizationManagerException;
import com.volkan.exception.EErrorType;
import com.volkan.repository.entity.Zone;
import com.volkan.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.volkan.constants.ApiUrls.*;

@RestController
@RequestMapping(ZONE)
@RequiredArgsConstructor
public class ZoneController {
    private final ZoneService zoneService;

    @PostMapping(CREATE)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<ZoneResponseDto> createZone(@RequestParam String name) {
        if (name.isBlank())
            throw new DistrictAndAuthorizationManagerException(EErrorType.INVALID_PARAMETER);
        ZoneResponseDto responseDto = zoneService.createZone(name);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(FIND_ALL)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<List<Zone>> getAllZones() {
        List<Zone> zones = zoneService.findAll();
        return ResponseEntity.ok(zones);
    }

    @GetMapping(FIND_BY_ID+"/{zoneId}")
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<?> getZoneById(@PathVariable Long zoneId) {
        Optional<Zone> zone = zoneService.findById(zoneId);
        return ResponseEntity.ok(zone.isEmpty()? "Zone bulunamadı":zone.get());
    }

    @PutMapping(UPDATE+"/{zoneId}")
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<ZoneResponseDto> updateZone(
            @PathVariable Long zoneId,
            @RequestBody @Valid UpdateZoneRequestDto updateDto
    ) {
        ZoneResponseDto updatedZone = zoneService.updateZone(zoneId,updateDto);
        return ResponseEntity.ok(updatedZone);
    }

    @DeleteMapping(DELETE_BY_ID+"/{zoneId}")
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<Void> deleteZone(@PathVariable Long zoneId) {
        zoneService.deleteById(zoneId);
        return ResponseEntity.noContent().build();
    }

}
