package com.volkan.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateVehicleRequestDto {
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Builder.Default
    private Long authorizationId = 0L;
}
