package com.stock.api;

import com.remc.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/26 21:24
 */
@RequestMapping("/StockServiceApi")
public interface StockServiceApi {

    /**
     * 查询库存扣减情况
     * @param bizId
     * @return
     */
    @RequestMapping("/queryStockStatus")
    Result<String> queryStockStatus(@RequestParam String bizId);
}
