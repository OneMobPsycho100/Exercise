package com.schedule.controller;

import com.order.api.OrderServiceApi;
import com.order.dto.orderDto;
import com.remc.common.Constants;
import com.remc.common.Result;
import com.remc.service.RabbitMQService;
import com.schedule.api.OrderTaskApi;
import com.schedule.service.TaskDelayInstApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/25 16:29
 */
@RestController
public class TaskOrderController implements OrderTaskApi {

    @Autowired
    private OrderServiceApi orderServiceApi;
    @Autowired
    private TaskDelayInstApiService delayInstApiService;
    @Autowired
    private RabbitMQService rabbitMQService;

    @Override
    public Result<String> queryOrderExist(String bizId) {
        orderDto orderDto = orderServiceApi.queryOrderExist(bizId).getResult();
        if (orderDto == null) {
            delayInstApiService.createDelayTask(bizId,"QUERY_ORDER");
        } else {
            rabbitMQService.sendMessage(Constants.ROUTERKEY_ORDER,orderDto);
        }
        return Result.getSuccess();
    }
}
