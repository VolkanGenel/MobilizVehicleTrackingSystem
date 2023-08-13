package com.volkan.rabbitmq.producer;

import com.volkan.rabbitmq.model.AuthorizationUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationUserProducer {
    @Value("${rabbitmq.exchange-districtAndAuthorization}")
    private String exchange;
    @Value("${rabbitmq.authorizationUserKey}")
    private String authorizationUserBindingKey;

    private final RabbitTemplate rabbitTemplate;
    public void sendAuthorization(AuthorizationUserModel model) {
        System.out.println("************************");
        rabbitTemplate.convertAndSend(exchange,authorizationUserBindingKey,model);
        System.out.println("************************");
    }
}
