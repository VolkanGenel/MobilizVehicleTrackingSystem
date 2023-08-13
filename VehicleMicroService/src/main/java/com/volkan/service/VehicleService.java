package com.volkan.service;

import com.volkan.dto.request.CreateVehicleRequestDto;
import com.volkan.dto.request.UpdateVehicleRequestDto;
import com.volkan.dto.response.VehicleResponseDto;
import com.volkan.exception.EErrorType;
import com.volkan.exception.VehicleManagerException;
import com.volkan.mapper.IVehicleMapper;
import com.volkan.rabbitmq.model.AuthorizationVehicleModel;
import com.volkan.repository.IVehicleRepository;
import com.volkan.repository.entity.Vehicle;
import com.volkan.repository.enums.ERole;
import com.volkan.utility.JwtTokenManager;
import com.volkan.utility.ServiceManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService extends ServiceManager<Vehicle,Long> {
    private final IVehicleRepository vehicleRepository;
    private final JwtTokenManager tokenManager;


    public VehicleService(IVehicleRepository vehicleRepository, JwtTokenManager tokenManager) {
        super(vehicleRepository);
        this.vehicleRepository = vehicleRepository;
        this.tokenManager = tokenManager;
    }


    public VehicleResponseDto createVehicle(CreateVehicleRequestDto dto) {
        if (vehicleRepository.isPlate(dto.getPlate()))
            throw new VehicleManagerException(EErrorType.VEHICLE_DUPLICATE);
        Vehicle vehicle = IVehicleMapper.INSTANCE.toVehicle(dto);
        save(vehicle);
        VehicleResponseDto responseDto = IVehicleMapper.INSTANCE.toVehicleResponseDto(vehicle);
        return responseDto;
    }
    public VehicleResponseDto updateVehicle(Long vehicleId,UpdateVehicleRequestDto dto) {
        Optional<Vehicle> vehicle = findById(vehicleId);
        if (vehicle.isEmpty())
            throw new VehicleManagerException(EErrorType.VEHICLE_NOT_FOUND);
        Vehicle updateVehicle = IVehicleMapper.INSTANCE.toVehicle(dto);
        updateVehicle.setVehicleId(vehicle.get().getVehicleId());
        update(updateVehicle);
        VehicleResponseDto responseDto = IVehicleMapper.INSTANCE.toVehicleResponseDto(updateVehicle);
        return responseDto;
    }

    public Optional<String> getTokenFindById(Long vehicleId) {
        Optional<Vehicle> vehicle = findById(vehicleId);
        if (vehicle.isEmpty())
            throw new VehicleManagerException(EErrorType.VEHICLE_NOT_FOUND);
        Optional<String> token = tokenManager.createToken(vehicleId);
        if (token.isEmpty())
            throw new VehicleManagerException(EErrorType.TOKEN_CREATION_ERROR);
        return token;
    }

    public ResponseEntity<Void> authorizeVehicle(AuthorizationVehicleModel model) {
        Optional<Vehicle> vehicle = findById(model.getVehicleId());
        if (vehicle.isEmpty())
            throw new VehicleManagerException(EErrorType.VEHICLE_NOT_FOUND);
        vehicle.get().setAuthorizationId(model.getAuthorizationId());
        update(vehicle.get());
        return ResponseEntity.ok().build();
    }

    public List<Vehicle> findAllVehicles(String token) {
        List<Vehicle> vehicles = findAll();
        Optional<String> role = tokenManager.getRoleFromToken(token);
        if(role.get().equals(ERole.COMPANYADMIN.toString())) {
            return vehicles;
        }
        List<Long> allowedList = tokenManager.getAllowedListFromToken(token);
        List<Vehicle> filteredVehicles = vehicles.stream().filter(vehicle -> allowedList.contains(vehicle.getAuthorizationId())).toList();
        filteredVehicles.forEach(System.out::println);
        //System.out.println(role);
        //allowedList.forEach(System.out::println);
        return filteredVehicles;
    }
}
