package com.schedule.listener;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.schedule.entity.DelayinstWorkInfo;
import com.schedule.service.TaskDelayinstService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 监听延时任务队列进行处理
 * @Author: chenmingzhe
 * @Date: 2020/4/24 17:15
 */
public class MessageQueueListener implements ChannelAwareMessageListener {

    @Autowired
    private TaskDelayinstService delayinstService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        DelayinstWorkInfo workInfo = JSONObject.parseObject(message.getBody(),DelayinstWorkInfo.class);
        // 对任务实例调用
        delayinstService.transferInstance(workInfo);
    }
}
