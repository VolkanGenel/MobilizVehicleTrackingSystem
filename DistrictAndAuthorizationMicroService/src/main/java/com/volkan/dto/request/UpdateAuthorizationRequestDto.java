package com.volkan.dto.request;


import com.volkan.repository.enums.ECity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAuthorizationRequestDto {
    private Long authorizationId;
    @Enumerated(EnumType.STRING)
    private ECity eCity;
    private Long zoneId;
    private Long sectorId;
}
