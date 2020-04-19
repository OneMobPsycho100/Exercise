package com.remc.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/19 10:20
 */
public class MessageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String bizid;
    private String biztype;
    private int status;
    private Object data;
    private Date createtime;

    public MessageDto() {
    }

    public MessageDto(String bizid, String biztype, int status, Object data, Date createtime) {
        this.bizid = bizid;
        this.biztype = biztype;
        this.status = status;
        this.data = data;
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "bizid='" + bizid + '\'' +
                ", biztype='" + biztype + '\'' +
                ", status=" + status +
                ", data=" + data +
                ", createtime=" + createtime +
                '}';
    }

    public String getBizid() {
        return bizid;
    }

    public void setBizid(String bizid) {
        this.bizid = bizid;
    }

    public String getBiztype() {
        return biztype;
    }

    public void setBiztype(String biztype) {
        this.biztype = biztype;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

}
