package com.volkan.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationVehicleModel implements Serializable {
    private Long authorizationId;
    private Long vehicleId;
}