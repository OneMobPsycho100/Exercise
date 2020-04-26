package com.stock.lintener;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.remc.common.Constants;
import com.remc.common.IdWorker;
import com.remc.dto.MessageDto;
import com.remc.service.RabbitMQService;
import com.stock.entity.Order;
import com.stock.entity.StockLog;
import com.stock.service.StockLogService;
import com.stock.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**监听扣减库存的消息
 * @Author: chenmingzhe
 * @Date: 2020/4/19 16:53
 */
public class MessageQueueListener implements ChannelAwareMessageListener {

    private Logger logger = LoggerFactory.getLogger(MessageQueueListener.class);

    @Autowired
    private StockService stockService;
    @Autowired
    private StockLogService stockLogService;
    @Autowired
    private RabbitMQService rabbitMqService;
    @Autowired
    private IdWorker idWorker;

    @Override
    public void onMessage(Message message, Channel channel) {
        try {
            Order order = JSONObject.parseObject(new String(message.getBody(),
                    StandardCharsets.UTF_8), Order.class);
            // bizId是唯一键，用于做好接口幂等性
            String bizId = order.getOrderid();
            StockLog stockLog = stockLogService
                    .lambdaQuery().eq(StockLog::getBizid, bizId).one();
            if (stockLog != null) {
                return;
            }
            stockService.deduction(order.getGoodname());
            stockLogService.save(new StockLog(idWorker.nextId(), bizId));
            // 发送消息修改状态为结束
            MessageDto messageDto = new MessageDto();
            messageDto.setBizid(bizId);
            messageDto.setBiztype(Constants.BizType.TYPE_ORDER_STOCK);
            messageDto.setCreatetime(new Date());
            messageDto.setStatus(Constants.MessageStatus.STATUS_END);
            rabbitMqService.sendMessage(Constants.ROUTERKEY_ORDER, messageDto);
        } catch (Exception e) {
            logger.error("出错了：{}", e.getMessage());
        }
    }
}
