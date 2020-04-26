package com.stock.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 库存扣减LOG
 * </p>
 *
 * @author chenmingzhe
 * @since 2020-04-26
 */
@TableName("t_stock_log")
public class StockLog  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId("stocklogid")
    private String stocklogid;

    /**
     * BIZID
     */
    @TableField("bizid")
    private String bizid;


    public String getStocklogid() {
        return stocklogid;
    }

    public void setStocklogid(String stocklogid) {
        this.stocklogid = stocklogid;
    }

    public String getBizid() {
        return bizid;
    }

    public void setBizid(String bizid) {
        this.bizid = bizid;
    }

    public StockLog(String stocklogid, String bizid) {
        this.stocklogid = stocklogid;
        this.bizid = bizid;
    }

    @Override
    public String toString() {
        return "StockLog{" +
        "stocklogid=" + stocklogid +
        ", bizid=" + bizid +
        "}";
    }
}
