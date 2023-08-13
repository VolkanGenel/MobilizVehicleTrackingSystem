package com.volkan.controller;

import com.volkan.dto.request.CreateSectorRequestDto;
import com.volkan.dto.request.UpdateSectorRequestDto;
import com.volkan.dto.response.SectorResponseDto;
import com.volkan.repository.entity.Sector;
import com.volkan.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.volkan.constants.ApiUrls.*;

@RestController
@RequestMapping(SECTOR)
public class SectorController {
    private final SectorService sectorService;

    @Autowired
    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @PostMapping(CREATE)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<SectorResponseDto> createSector(@RequestBody @Valid CreateSectorRequestDto createDto) {
        SectorResponseDto responseDto = sectorService.createSector(createDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(FIND_ALL)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<List<Sector>> getAllSectors() {
        List<Sector> authorizations = sectorService.findAll();
        return ResponseEntity.ok(authorizations);
    }

    @GetMapping(FIND_BY_ID)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<?> getSectorById(@RequestParam Long sectorid) {
        Optional<Sector> sector = sectorService.findById(sectorid);
        return ResponseEntity.ok(sector.isEmpty()? "Sektör bulunamadı":sector.get());
    }

    @PutMapping(UPDATE)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<SectorResponseDto> updateSector(
            @RequestParam Long sectorId,
            @RequestBody @Valid UpdateSectorRequestDto updateDto
    ) {
        SectorResponseDto updatedSector = sectorService.updateSector(sectorId,updateDto);
        return ResponseEntity.ok(updatedSector);
    }

    @DeleteMapping(DELETE_BY_ID+"/{sectorId}")
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<Void> deleteSector(@PathVariable Long sectorId) {
        sectorService.deleteById(sectorId);
        return ResponseEntity.noContent().build();
    }
}
