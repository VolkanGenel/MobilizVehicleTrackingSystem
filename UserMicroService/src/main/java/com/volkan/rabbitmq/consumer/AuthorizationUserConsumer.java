package com.volkan.rabbitmq.consumer;

import com.volkan.rabbitmq.model.AuthorizationUserModel;
import com.volkan.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizationUserConsumer {
    private final UserService userService;
    @RabbitListener(queues = "${rabbitmq.userQueue}")
    public void authorizeUser (AuthorizationUserModel model){
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        log.info("User {}",model.toString());
        userService.authorizeUser(model);
    }
}
