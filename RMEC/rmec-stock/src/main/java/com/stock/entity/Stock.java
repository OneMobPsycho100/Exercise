package com.stock.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author chenmingzhe
 * @since 2020-04-18
 */
@TableName("t_stock")
public class Stock  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("stockid")
    private String stockid;

    @TableField("goodname")
    private String goodname;

    @TableField("stock")
    private Integer stock;

    @TableField("version")
    private String version;

    @TableField("updatetime")
    private LocalDateTime updatetime;

    @TableField("createtime")
    private LocalDateTime createtime;


    public String getStockid() {
        return stockid;
    }

    public void setStockid(String stockid) {
        this.stockid = stockid;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDateTime getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(LocalDateTime updatetime) {
        this.updatetime = updatetime;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Stock{" +
        "stockid=" + stockid +
        ", goodname=" + goodname +
        ", stock=" + stock +
        ", version=" + version +
        ", updatetime=" + updatetime +
        ", createtime=" + createtime +
        "}";
    }
}
