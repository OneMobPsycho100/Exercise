package com.stock.mapper;

import com.stock.entity.Stock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenmingzhe
 * @since 2020-04-18
 */
public interface StockMapper extends  BaseMapper<Stock> {

    /**
     * 扣减库存
     * @param goodname
     */

    void deductionByName(@Param("goodname") String goodname);
}
