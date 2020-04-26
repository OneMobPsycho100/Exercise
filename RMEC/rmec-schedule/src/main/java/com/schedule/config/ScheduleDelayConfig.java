package com.schedule.config;

import com.remc.common.Constants;
import com.remc.config.RmecConfiguration;
import com.schedule.listener.MessageQueueListener;
import com.schedule.service.DelayTaskService;
import com.schedule.service.TaskDelayinstService;
import com.schedule.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/19 12:48
 */
@Configuration
@Import({RmecConfiguration.class})
public class ScheduleDelayConfig {

    private Logger logger = LoggerFactory.getLogger(ScheduleDelayConfig.class);

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
        container.setMessageListener(queueListener);
        container.setQueueNames(Constants.QUEUE_TASK_DELAY);
        logger.info("queues {}", Arrays.toString(container.getQueueNames()));
        return container;
    }

    @Bean
    public DelayTaskService delayTaskService() {
        return new DelayTaskService();
    }

    @Bean
    public TaskDelayinstService taskDelayinstService() {
        return new TaskDelayinstService();
    }

    @Bean
    public TaskService taskService() {
        return new TaskService();
    }
}
