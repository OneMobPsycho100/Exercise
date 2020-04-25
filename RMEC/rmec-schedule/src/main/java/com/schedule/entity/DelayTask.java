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
 * @since 2020-04-23
 */
@TableName("t_delay_task")
public class DelayTask  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("delayid")
    private String delayid;

    @TableField("name")
    private String name;

    @TableField("description")
    private String description;

    @TableField("beanname")
    private String beanname;

    @TableField("methodname")
    private String methodname;

    @TableField("queuename")
    private String queuename;

    @TableField("delaytime")
    private Integer delaytime;

    @TableField("status")
    private String status;


    public String getDelayid() {
        return delayid;
    }

    public void setDelayid(String delayid) {
        this.delayid = delayid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getQueuename() {
        return queuename;
    }

    public void setQueuename(String queuename) {
        this.queuename = queuename;
    }

    public Integer getDelaytime() {
        return delaytime;
    }

    public void setDelaytime(Integer delaytime) {
        this.delaytime = delaytime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DelayTask{" +
        "delayid=" + delayid +
        ", name=" + name +
        ", description=" + description +
        ", beanname=" + beanname +
        ", methodname=" + methodname +
        ", queuename=" + queuename +
        ", delaytime=" + delaytime +
        ", status=" + status +
        "}";
    }
}
