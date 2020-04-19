package com.remc.config;

import com.remc.common.Constants;
import com.remc.common.IdWorker;
import com.remc.service.RabbitMqService;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/18 19:31
 */
@Configuration
public class RmecConfiguration {

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Bean
    public RabbitMqService rabbitMqService() {
        return new RabbitMqService();
    }


    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

}
