package com.remc.service;

import com.alibaba.fastjson.JSONObject;
import com.remc.common.Constants;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.SmartLifecycle;

import java.util.Properties;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/18 22:55
 */
public class RabbitMqService implements SmartLifecycle {

    public static final String ROUTERKEY_ORDER = "order";
    public static final String ROUTERKEY_STOCK = "STOCK";
    public static final String QUEUE_STOCK = "remc.stock";
    public static final String QUEUE_ORDER = "remc.order";

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    @Qualifier("remcMessageContainer")
    private SimpleMessageListenerContainer listenerContainer;

    @Autowired
    @Qualifier("remcExchange")
    private DirectExchange directExchange;

    @Override
    public void start() {
        addNewQueue(QUEUE_ORDER, ROUTERKEY_ORDER);
        addNewQueue(QUEUE_STOCK, ROUTERKEY_STOCK);
    }

    public void addNewQueue(String queueName, String routerKey) {
        Properties properties = this.rabbitAdmin.getQueueProperties(queueName);
        Queue queue = new Queue(queueName);
        if (properties == null) {
            rabbitAdmin.declareQueue(queue);
            listenerContainer.addQueues(queue);
        }
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).with(routerKey));
    }

    public void sendMessage(String routerKey, Object data) {
        rabbitAdmin.getRabbitTemplate().convertAndSend(directExchange.getName(), routerKey, JSONObject.toJSONString(data));
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
