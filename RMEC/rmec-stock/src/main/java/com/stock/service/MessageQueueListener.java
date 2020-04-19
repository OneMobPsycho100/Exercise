package com.stock.service;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.remc.common.Constants;
import com.remc.dto.MessageDto;
import com.remc.service.RabbitMqService;
import com.stock.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/19 16:53
 */
public class MessageQueueListener implements ChannelAwareMessageListener {

    private Logger logger = LoggerFactory.getLogger(MessageQueueListener.class);

    @Autowired
    private StockService stockService;
    @Autowired
    private RabbitMqService rabbitMqService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            Order order = JSONObject.parseObject(new String(message.getBody(),
                    StandardCharsets.UTF_8), Order.class);

            stockService.deduction(order.getGoodname());
            MessageDto messageDto = new MessageDto();
            messageDto.setBizid(order.getOrderid());
            messageDto.setBiztype(Constants.BizType.TYPE_ORDER_STOCK);
            messageDto.setCreatetime(new Date());
            messageDto.setStatus(Constants.MessageStatus.STATUS_END);
            rabbitMqService.sendMessage(Constants.ROUTERKEY_ORDER, messageDto);
        } catch (Exception e) {
            logger.error("出错了：{}", e.getMessage());
        }
    }
}
