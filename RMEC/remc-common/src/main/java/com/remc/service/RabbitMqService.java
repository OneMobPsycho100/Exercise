package com.remc.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/18 22:55
 */
public class RabbitMqService {

    private Logger logger = LoggerFactory.getLogger(RabbitMqService.class);

    public static final String ROUTERKEY_ORDER = "order";
    public static final String ROUTERKEY_STOCK = "STOCK";
    public static final String QUEUE_STOCK = "remc.stock";
    public static final String QUEUE_ORDER = "remc.order";

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    private DirectExchange directExchange;

    public void addNewQueue(String queueName, String routerKey) {
        Properties properties = this.rabbitAdmin.getQueueProperties(queueName);
        Queue queue = new Queue(queueName);
        if (properties == null) {
            rabbitAdmin.declareQueue(queue);
          //  listenerContainer.addQueues(queue);
        }
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).with(routerKey));
    }

    public void sendMessage(String routerKey, Object data) {
        logger.info(JSONObject.toJSONString(data));
        rabbitAdmin.getRabbitTemplate().convertAndSend(directExchange.getName(),
                routerKey, JSONObject.toJSONString(data));
    }

}
