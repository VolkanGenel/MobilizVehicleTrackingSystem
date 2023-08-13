package com.volkan.dto.request;


import com.volkan.repository.enums.ECity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAuthorizationRequestDto {
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ECity eCity = ECity.ADANA;
    private Long zoneId;
    private Long sectorId;
}
