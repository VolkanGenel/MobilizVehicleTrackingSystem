package com.volkan.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.userQueue}")
    private String userQueueAuthorization;
    @Bean
    Queue userAuthorizationQueue() {
        return new Queue(userQueueAuthorization);
    }

}
