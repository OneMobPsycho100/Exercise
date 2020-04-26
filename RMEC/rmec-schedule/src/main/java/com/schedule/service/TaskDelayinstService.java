package com.schedule.service;

import com.remc.common.IdWorker;
import com.schedule.common.DelayInstStatusEnum;
import com.schedule.entity.DelayTask;
import com.schedule.entity.DelayinstWorkInfo;
import com.schedule.entity.TaskDelayinst;
import com.schedule.mapper.TaskDelayinstMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chenmingzhe
 * @since 2020-04-24
 */
public class TaskDelayinstService extends ServiceImpl<TaskDelayinstMapper, TaskDelayinst> {

    private Logger logger = LoggerFactory.getLogger(TaskDelayinstService.class);

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private ApplicationContext applicationContext;

    public LocalDateTime saveDelayInst(String bizId, DelayTask delayTask) {
        LocalDateTime plannedTime = LocalDateTime.now()
                .plusSeconds(delayTask.getDelaytime());
        TaskDelayinst delayInst = TaskDelayinst
                .newBuilder().delayworkid(idWorker.nextId()).delayid(delayTask.getDelayid())
                .bizid(bizId)
                .plannedtime(plannedTime
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .status(DelayInstStatusEnum.NOT_RUN.getCode())
                .build();
        this.save(delayInst);
        return plannedTime;
    }

    public List<DelayinstWorkInfo> queryInstByStatusAndTime(String status, Date startTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return this.baseMapper.queryInstByStatusAndTime(status, dateFormat.format(startTime));
    }

    public void updateStatusById(String code, String delayWorkId) {
        this.lambdaUpdate().set(TaskDelayinst::getStatus, code)
                .eq(TaskDelayinst::getDelayworkid, delayWorkId).update();
    }

    public void transferInstance(DelayinstWorkInfo workInfo) {
        boolean isSucceed = true;
        LocalDateTime before = LocalDateTime.now();
        TaskDelayinst delayInst = this.getById(workInfo.getDelayworkid());
        try {
            if (DelayInstStatusEnum.WAIT_RUN.getCode()
                    .equals(delayInst.getStatus())) {
                delayInst.setStatus(DelayInstStatusEnum.RUNNING.getCode());
                this.updateById(delayInst);
                Object bean = applicationContext.getBean(workInfo.getBeanname());
                Method method = bean.getClass().getMethod(workInfo.getMethodname(), String.class);
                method.invoke(bean, workInfo.getBizId());
                logger.info("transfer{} {} {}", workInfo.getBeanname(), method, workInfo.getBizId());
            }
        } catch (Exception e) {
            isSucceed = false;
            logger.error("method call error", e);
        } finally {
            delayInst.setStatus(DelayInstStatusEnum.COMPLETE.getCode());
            delayInst.setIssuc(isSucceed ? "1" : "0");
            delayInst.setRuntime(before.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            this.updateById(delayInst);
        }

    }
}
