package com.volkan.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.vehicleQueue}")
    private String vehicleQueueAuthorization;
    @Bean
    Queue vehicleAuthorizationQueue() {
        return new Queue(vehicleQueueAuthorization);
    }
}





