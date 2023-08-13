package com.volkan.rabbitmq.producer;

import com.volkan.rabbitmq.model.AuthorizationVehicleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationVehicleProducer {
    @Value("${rabbitmq.exchange-districtAndAuthorization}")
    private String exchange;
    @Value("${rabbitmq.authorizationVehicleKey}")
    private String authorizationVehicleBindingKey;

    private final RabbitTemplate rabbitTemplate;
    public void sendAuthorization(AuthorizationVehicleModel model) {
        rabbitTemplate.convertAndSend(exchange,authorizationVehicleBindingKey,model);
    }
}
