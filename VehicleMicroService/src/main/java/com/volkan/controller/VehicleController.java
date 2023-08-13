package com.volkan.controller;

import com.volkan.dto.request.CreateVehicleRequestDto;
import com.volkan.dto.request.UpdateVehicleRequestDto;
import com.volkan.dto.response.VehicleResponseDto;
import com.volkan.repository.entity.Vehicle;
import com.volkan.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


import java.util.List;
import java.util.Optional;

import static com.volkan.constants.ApiUrls.*;

@RestController
@RequestMapping(VEHICLE)
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping(CREATE_VEHICLE)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<VehicleResponseDto> createVehicle(@RequestBody @Valid CreateVehicleRequestDto createDto) {
        VehicleResponseDto responseDto = vehicleService.createVehicle(createDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Vehicle>> getAllVehicles(String token) {
        List<Vehicle> vehicles = vehicleService.findAllVehicles(token);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping(FIND_BY_ID)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<?> getVehicleById(@RequestParam Long vehicleId) {
        Optional<Vehicle> vehicle = vehicleService.findById(vehicleId);
        return ResponseEntity.ok(vehicle.isEmpty()? "Araç bulunamadı":vehicle.get());
    }

    @PutMapping(UPDATE_VEHICLE+"/{vehicleId}")
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<VehicleResponseDto> updateVehicle(
            @PathVariable Long vehicleId,
            @RequestBody @Valid UpdateVehicleRequestDto updateDto
    ) {
        VehicleResponseDto updatedVehicle = vehicleService.updateVehicle(vehicleId,updateDto);
        return ResponseEntity.ok(updatedVehicle);
    }

    @DeleteMapping(DELETE_BY_ID+"/{vehicleId}")
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("vehicleId") Long vehicleId) {
        vehicleService.deleteById(vehicleId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(GET_TOKEN_FIND_BY_ID)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<String> getTokenFindById(@RequestParam Long vehicleId) {
        return ResponseEntity.ok(vehicleService.getTokenFindById(vehicleId).get());
    }
}
