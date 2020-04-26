package com.order.controller;

import com.order.api.OrderServiceApi;
import com.order.dto.orderDto;
import com.order.entity.Order;
import com.order.service.OrderService;
import com.remc.common.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/25 16:44
 */
@RestController
public class OrderApiController implements OrderServiceApi {

    @Autowired
    private OrderService orderService;

    @Override
    public Result<orderDto> queryOrderExist(String bizId) {
        Order order = orderService
                .lambdaQuery().eq(Order::getOrderid, bizId)
                .one();
        orderDto orderDto = new orderDto();
        BeanUtils.copyProperties(order, orderDto);
        return Result.getSuccess(orderDto);
    }
}
