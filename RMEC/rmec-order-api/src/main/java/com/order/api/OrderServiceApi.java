package com.order.api;

import com.order.dto.orderDto;
import com.remc.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/25 16:40
 */
@RequestMapping("/OrderServiceApi")
public interface OrderServiceApi {

    /**
     * 查询订单是否存在
     * @param bizId
     * @return
     */
    @RequestMapping("/queryOrderExist")
    Result<orderDto> queryOrderExist(@RequestParam String bizId);
}
