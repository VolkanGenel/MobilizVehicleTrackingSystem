package com.volkan.rabbitmq.consumer;

import com.volkan.rabbitmq.model.AuthorizationVehicleModel;
import com.volkan.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizationVehicleConsumer {
    private final VehicleService vehicleService;
    @RabbitListener(queues = "${rabbitmq.vehicleQueue}")
    public void authorizeVehicle (AuthorizationVehicleModel model) {
        log.info("Vehicle {}",model.toString());
        vehicleService.authorizeVehicle(model);
    }
}
