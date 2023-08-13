package com.volkan.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.exchange-districtAndAuthorization}")
    private String exchange;
    @Value("${rabbitmq.authorizationUserKey}")
    private String authorizationUserBindingKey;
    @Value("${rabbitmq.userQueue}")
    private String userQueueAuthorization;
    @Value("${rabbitmq.authorizationVehicleKey}")
    private String authorizationVehicleBindingKey;
    @Value("${rabbitmq.vehicleQueue}")
    private String vehicleQueueAuthorization;

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }
    @Bean
    Queue userAuthorizationQueue() {
        return new Queue(userQueueAuthorization);
    }
    @Bean
    public Binding bindingAuthorizationUser(final Queue userAuthorizationQueue, final DirectExchange exchangeAuthorization) {
        return BindingBuilder.bind(userAuthorizationQueue).to(exchangeAuthorization).with(authorizationUserBindingKey);
    }
    @Bean
    Queue vehicleAuthorizationQueue() {
        return new Queue(vehicleQueueAuthorization);
    }
    @Bean
    public Binding bindingAuthorizationVehicle(final Queue vehicleAuthorizationQueue, final DirectExchange exchangeAuthorization) {
        return BindingBuilder.bind(vehicleAuthorizationQueue).to(exchangeAuthorization).with(authorizationVehicleBindingKey);
    }

}
