package com.schedule.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author chenmingzhe
 * @since 2020-04-24
 */
@TableName("t_task_delayinst")
public class TaskDelayinst  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("delayworkid")
    private String delayworkid;

    @TableField("delayid")
    private String delayid;

    @TableField("bizid")
    private String bizid;

    @TableField("plannedtime")
    private String plannedtime;

    @TableField("runtime")
    private String runtime;

    @TableField("status")
    private String status;

    @TableField("issuc")
    private String issuc;

    public TaskDelayinst() {
    }

    private TaskDelayinst(Builder builder) {
        setDelayworkid(builder.delayworkid);
        setDelayid(builder.delayid);
        setBizid(builder.bizid);
        setPlannedtime(builder.plannedtime);
        setRuntime(builder.runtime);
        setStatus(builder.status);
        setIssuc(builder.issuc);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getDelayworkid() {
        return delayworkid;
    }

    public void setDelayworkid(String delayworkid) {
        this.delayworkid = delayworkid;
    }

    public String getDelayid() {
        return delayid;
    }

    public void setDelayid(String delayid) {
        this.delayid = delayid;
    }

    public String getBizid() {
        return bizid;
    }

    public void setBizid(String bizid) {
        this.bizid = bizid;
    }

    public String getPlannedtime() {
        return plannedtime;
    }

    public void setPlannedtime(String plannedtime) {
        this.plannedtime = plannedtime;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIssuc() {
        return issuc;
    }

    public void setIssuc(String issuc) {
        this.issuc = issuc;
    }

    @Override
    public String toString() {
        return "TaskDelayinst{" +
        "delayworkid=" + delayworkid +
        ", delayid=" + delayid +
        ", bizid=" + bizid +
        ", plannedtime=" + plannedtime +
        ", runtime=" + runtime +
        ", status=" + status +
        ", issuc=" + issuc +
        "}";
    }

    public static final class Builder {
        private String delayworkid;
        private String delayid;
        private String bizid;
        private String plannedtime;
        private String runtime;
        private String status;
        private String issuc;

        private Builder() {
        }

        public Builder delayworkid(String delayworkid) {
            this.delayworkid = delayworkid;
            return this;
        }

        public Builder delayid(String delayid) {
            this.delayid = delayid;
            return this;
        }

        public Builder bizid(String bizid) {
            this.bizid = bizid;
            return this;
        }

        public Builder plannedtime(String plannedtime) {
            this.plannedtime = plannedtime;
            return this;
        }

        public Builder runtime(String runtime) {
            this.runtime = runtime;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder issuc(String issuc) {
            this.issuc = issuc;
            return this;
        }

        public TaskDelayinst build() {
            return new TaskDelayinst(this);
        }
    }
}
