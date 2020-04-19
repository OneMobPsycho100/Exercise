package com.msg.service;

import com.msg.entity.MessageInfo;
import com.remc.dto.MessageDto;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/19 15:31
 */
public interface MessageHandler {

    /**
     * 处理消息
     * @param messageDto
     */
    public void process(MessageDto messageDto);

    /**
     * 获取业务类型
     * @return
     */
    public String getBizType();
}
