package com.schedule.entity;

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
 * @since 2020-04-23
 */
@TableName("t_task")
public class Task  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("taskid")
    private String taskid;

    @TableField("taskname")
    private String taskname;

    @TableField("caption")
    private String caption;

    @TableField("cron")
    private String cron;

    /**
     * 1普通
     */
    @TableField("type")
    private String type;

    @TableField("beanname")
    private String beanname;

    @TableField("ifcname")
    private String ifcname;

    @TableField("maxfailtimes")
    private Integer maxfailtimes;

    /**
     * 0正常，1锁定
     */
    @TableField("taskstatus")
    private String taskstatus;

    /**
     * 0停止，1启动
     */
    @TableField("taskenabled")
    private String taskenabled;

    @TableField("failtimes")
    private Integer failtimes;

    @TableField("createtime")
    private LocalDateTime createtime;

    @TableField("updatetime")
    private LocalDateTime updatetime;


    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBeanname() {
        return beanname;
    }

    public void setBeanname(String beanname) {
        this.beanname = beanname;
    }

    public String getIfcname() {
        return ifcname;
    }

    public void setIfcname(String ifcname) {
        this.ifcname = ifcname;
    }

    public Integer getMaxfailtimes() {
        return maxfailtimes;
    }

    public void setMaxfailtimes(Integer maxfailtimes) {
        this.maxfailtimes = maxfailtimes;
    }

    public String getTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(String taskstatus) {
        this.taskstatus = taskstatus;
    }

    public String getTaskenabled() {
        return taskenabled;
    }

    public void setTaskenabled(String taskenabled) {
        this.taskenabled = taskenabled;
    }

    public Integer getFailtimes() {
        return failtimes;
    }

    public void setFailtimes(Integer failtimes) {
        this.failtimes = failtimes;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public LocalDateTime getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(LocalDateTime updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "Task{" +
        "taskid=" + taskid +
        ", taskname=" + taskname +
        ", caption=" + caption +
        ", cron=" + cron +
        ", type=" + type +
        ", beanname=" + beanname +
        ", ifcname=" + ifcname +
        ", maxfailtimes=" + maxfailtimes +
        ", taskstatus=" + taskstatus +
        ", taskenabled=" + taskenabled +
        ", failtimes=" + failtimes +
        ", createtime=" + createtime +
        ", updatetime=" + updatetime +
        "}";
    }
}
