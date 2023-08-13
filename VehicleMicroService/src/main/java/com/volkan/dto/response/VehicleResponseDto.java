package com.volkan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseDto {
    private Long vehicleId;
    private String plate;
    private String brand;
    private String model;
    private Integer modelYear;
    private String chassisNumber;
    private String label;
}
