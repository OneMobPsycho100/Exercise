package com.order.entity;

import java.math.BigDecimal;
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
public class Order  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("orderid")
    private String orderid;

    @TableId("goodname")
    private String goodname;

    @TableField("price")
    private BigDecimal price;

    @TableField("userid")
    private String userid;

    @TableField("orderstatus")
    private String orderstatus;

    @TableField("updatetime")
    private LocalDateTime updatetime;

    @TableField("createtime")
    private LocalDateTime createtime;

    private Order(Builder builder) {
        setOrderid(builder.orderid);
        setGoodname(goodname);
        setPrice(builder.price);
        setUserid(builder.userid);
        setOrderstatus(builder.orderstatus);
        setUpdatetime(builder.updatetime);
        setCreatetime(builder.createtime);
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
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

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    @Override
    public String toString() {
        return "Order{" +
        "orderid=" + orderid +
        ", price=" + price +
        ", userid=" + userid +
        ", orderstatus=" + orderstatus +
        ", updatetime=" + updatetime +
        ", createtime=" + createtime +
        ", goodname=" + goodname +
        "}";
    }


    public static final class Builder {
        private String orderid;
        private String goodname;
        private BigDecimal price;
        private String userid;
        private String orderstatus;
        private LocalDateTime updatetime;
        private LocalDateTime createtime;

        private Builder() {
        }

        public Builder orderid(String orderid) {
            this.orderid = orderid;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder userid(String userid) {
            this.userid = userid;
            return this;
        }

        public Builder orderstatus(String orderstatus) {
            this.orderstatus = orderstatus;
            return this;
        }

        public Builder updatetime(LocalDateTime updatetime) {
            this.updatetime = updatetime;
            return this;
        }

        public Builder createtime(LocalDateTime createtime) {
            this.createtime = createtime;
            return this;
        }

        public Builder goodname(String goodname) {
            this.orderstatus = goodname;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
