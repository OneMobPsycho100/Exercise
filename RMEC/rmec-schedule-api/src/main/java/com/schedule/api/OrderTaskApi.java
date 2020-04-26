package com.schedule.api;

import com.remc.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/25 16:31
 */
@RequestMapping("/OrderTaskApi")
public interface OrderTaskApi {

    /**
     * 查询订单是否存在
     * @param bizId
     * @return
     */
    @RequestMapping("/queryOrderExist")
    Result<String> queryOrderExist(String bizId);
}
