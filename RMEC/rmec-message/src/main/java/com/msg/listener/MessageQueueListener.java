package com.msg.listener;

import com.alibaba.fastjson.JSONObject;
import com.msg.handler.MessageHandler;
import com.rabbitmq.client.Channel;
import com.remc.dto.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.SmartLifecycle;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/19 12:50
 */
public class MessageQueueListener implements ChannelAwareMessageListener, SmartLifecycle {

    private Logger logger = LoggerFactory.getLogger(MessageQueueListener.class);

    @Autowired
    private ApplicationContext applicationContext;
    private Map<String, MessageHandler> handlers = new ConcurrentHashMap<>();

    @Override
    public void start() {
        String[] beanNames = applicationContext.getBeanNamesForType(MessageHandler.class);
        for (String beanName : beanNames) {
            MessageHandler bean = (MessageHandler) applicationContext.getBean(beanName);
            handlers.put(bean.getBizType(), bean);
        }
    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            MessageDto messageDto = JSONObject
                    .parseObject(new String(message.getBody(), StandardCharsets.UTF_8), MessageDto.class);
            logger.info("消息体：{}", messageDto);
            MessageHandler messageHandler = handlers.get(messageDto.getBiztype());
            messageHandler.process(messageDto);
        } catch (Exception e) {
            logger.error("出错了: {}", e.getMessage());
        }

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
