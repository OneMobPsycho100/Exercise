package com.stock.service;

import com.stock.entity.Stock;
import com.stock.mapper.StockMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenmingzhe
 * @since 2020-04-18
 */
@Service
public class StockService extends  ServiceImpl<StockMapper, Stock> {

}
