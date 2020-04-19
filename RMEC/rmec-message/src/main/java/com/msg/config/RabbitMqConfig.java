package com.msg.config;

import com.msg.service.MessageQueueListener;
import com.remc.common.Constants;
import com.remc.service.RabbitMqService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/19 12:48
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public MessageQueueListener messageQueueListener() {
        return new MessageQueueListener();
    }

    @Bean
    public SimpleMessageListenerContainer messageContainer(MessageQueueListener queueListener, ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(3);
        container.setConcurrentConsumers(1);
        container.addQueues(new Queue(Constants.QUEUE_ORDER));
        container.setMessageListener(queueListener);
        return container;
    }
}
