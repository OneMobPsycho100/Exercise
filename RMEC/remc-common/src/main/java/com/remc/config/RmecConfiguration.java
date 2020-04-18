package com.remc.config;

import com.remc.common.IdWorker;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
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
    public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(3);
        container.setConcurrentConsumers(1);
   //     container.setMessageListener(queueListener);
        return container;
    }


}
