package com.schedule.api;

import com.remc.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/26 21:49
 */
@RequestMapping("/StockTaskApi")
public interface StockTaskApi {

    /**
     *查询库存扣减状态
     * @param bizId
     * @return
     */
    @RequestMapping("/queryStockInfo")
    Result<String> queryStockInfo(@RequestParam String bizId);
}
