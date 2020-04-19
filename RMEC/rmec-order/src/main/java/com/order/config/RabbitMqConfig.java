package com.order.config;

import com.remc.common.Constants;
import com.remc.service.RabbitMqService;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/19 12:48
 */
@Configuration
public class RabbitMqConfig {

    @Autowired
    private RabbitMqService rabbitMqService;

    @PostConstruct
    public void addNewQueue() {
        rabbitMqService.addNewQueue(Constants.QUEUE_ORDER,Constants.ROUTERKEY_ORDER);
    }
}
