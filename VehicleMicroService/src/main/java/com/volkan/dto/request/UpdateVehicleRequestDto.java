package com.volkan.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVehicleRequestDto {
    @NotBlank(message = "Plaka alanı zorunludur")
    private String plate;

    @NotBlank(message = "Marka alanı zorunludur")
    private String brand;

    @NotBlank(message = "Model alanı zorunludur")
    private String model;

    @NotNull(message = "Model yılı alanı zorunludur")
    private Integer modelYear;

    private String chassisNumber;
    private String label;
}
