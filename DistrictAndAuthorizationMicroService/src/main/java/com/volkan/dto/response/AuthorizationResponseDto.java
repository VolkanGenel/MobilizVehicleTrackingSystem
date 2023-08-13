package com.volkan.dto.response;

import com.volkan.repository.enums.ECity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationResponseDto {
    private Long authorizationId;
    private ECity eCity;
    private Long zoneId;
    private Long sectorId;
}
