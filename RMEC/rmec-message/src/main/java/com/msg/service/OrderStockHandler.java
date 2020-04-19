package com.msg.service;

import com.msg.entity.MessageInfo;
import com.remc.common.Constants;
import com.remc.dto.MessageDto;
import com.remc.service.RabbitMqService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/19 15:25
 */
@Service
public class OrderStockHandler implements MessageHandler {


    @Autowired
    private MessageService messageService;
    @Autowired
    private RabbitMqService rabbitMqService;

    @Override
    public void process(MessageDto messageDto) {
        MessageInfo messageInfo = new MessageInfo();
        BeanUtils.copyProperties(messageDto, messageInfo);
        switch (messageInfo.getStatus()) {
            case Constants.MessageStatus.STATUS_INIT:
                messageService.save(messageInfo);
                break;

            case Constants.MessageStatus.STATUS_SEND:
                messageService.lambdaUpdate()
                        .set(MessageInfo::getStatus, messageInfo.getStatus())
                        .eq(MessageInfo::getBizid, messageInfo.getBizid())
                        .update();
                rabbitMqService.sendMessage(Constants.ROUTERKEY_STOCK, messageDto.getData());
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
