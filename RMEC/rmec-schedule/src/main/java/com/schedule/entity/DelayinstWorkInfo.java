package com.schedule.entity;

/**
 * @author chenmingzhe
 * @since 2020-04-23
 */
public class DelayinstWorkInfo {

    private String bizId;
    private String delayworkid;
    private String beanname;
    private String methodname;
    private String code;
    private String queuename;

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getDelayworkid() {
        return delayworkid;
    }

    public void setDelayworkid(String delayworkid) {
        this.delayworkid = delayworkid;
    }

    public String getBeanname() {
        return beanname;
    }

    public void setBeanname(String beanname) {
        this.beanname = beanname;
    }

    public String getMethodname() {
        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQueuename() {
        return queuename;
    }

    public void setQueuename(String queuename) {
        this.queuename = queuename;
    }

    @Override
    public String toString() {
        return "DelayInstQueueInfo [bizId=" + bizId + ", delayinstid=" + delayworkid + ", beanname=" + beanname + ", methodname=" + methodname + ", code=" + code + ", queuename=" + queuename + "]";
    }

}
