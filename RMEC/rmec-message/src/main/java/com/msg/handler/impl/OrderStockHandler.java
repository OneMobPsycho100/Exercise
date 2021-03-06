package com.msg.handler.impl;

import com.msg.api.TaskDelayInstApiService;
import com.msg.entity.MessageInfo;
import com.msg.handler.MessageHandler;
import com.msg.service.MessageService;
import com.remc.common.Constants;
import com.remc.dto.MessageDto;
import com.remc.service.RabbitMQService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单库存消息处理
 * @Author: chenmingzhe
 * @Date: 2020/4/19 15:25
 */
@Service
public class OrderStockHandler implements MessageHandler {

    @Autowired
    private TaskDelayInstApiService delayInstApiService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private RabbitMQService rabbitMqService;

    @Override
    public void process(MessageDto messageDto) {
        MessageInfo messageInfo = new MessageInfo();
        BeanUtils.copyProperties(messageDto, messageInfo);
        String bizId = messageInfo.getBizid();
        switch (messageInfo.getStatus()) {
            case Constants.MessageStatus.STATUS_INIT:
                MessageInfo info = messageService
                        .lambdaQuery().eq(MessageInfo::getBizid, bizId).one();
                if (info == null) {
                    messageService.save(messageInfo);
                }
                // 开启任务查询订单状态
                delayInstApiService.createDelayTask(messageInfo.getBizid(), "QUERY_ORDER");
                break;

            case Constants.MessageStatus.STATUS_SEND:
                rabbitMqService.sendMessage(Constants.ROUTERKEY_STOCK, messageDto.getData());
                messageService.lambdaUpdate()
                        .eq(MessageInfo::getBizid,bizId)
                        .set(MessageInfo::getStatus,messageInfo.getStatus())
                        .update();
                // 开启任务查询库存扣减情况
                delayInstApiService.createDelayTask(messageInfo.getBizid(), "QUERY_STOCK");
                break;

            case Constants.MessageStatus.STATUS_END:
                messageService.lambdaUpdate().set(MessageInfo::getStatus, messageInfo.getStatus()).
                        eq(MessageInfo::getBizid, messageInfo.getBizid())
                        .update();
                break;

            default:
                throw new IllegalArgumentException("message types do not match");
        }
    }


    @Override
    public String getBizType() {
        return Constants.BizType.TYPE_ORDER_STOCK;
    }
}
