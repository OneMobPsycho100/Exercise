package com.msg.entity;

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
public class Message  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("msgid")
    private String msgid;

    @TableField("bizid")
    private String bizid;

    @TableField("status")
    private Integer status;

    @TableField("data")
    private String data;

    @TableField("biztype")
    private Integer biztype;

    @TableField("updatetime")
    private LocalDateTime updatetime;

    @TableField("createtime")
    private LocalDateTime createtime;


    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getBizid() {
        return bizid;
    }

    public void setBizid(String bizid) {
        this.bizid = bizid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getBiztype() {
        return biztype;
    }

    public void setBiztype(Integer biztype) {
        this.biztype = biztype;
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
        return "Message{" +
        "msgid=" + msgid +
        ", bizid=" + bizid +
        ", status=" + status +
        ", data=" + data +
        ", biztype=" + biztype +
        ", updatetime=" + updatetime +
        ", createtime=" + createtime +
        "}";
    }
}
