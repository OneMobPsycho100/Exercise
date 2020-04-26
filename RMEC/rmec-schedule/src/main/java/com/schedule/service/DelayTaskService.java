package com.schedule.service;

import com.schedule.common.Constants;
import com.schedule.entity.DelayTask;
import com.schedule.mapper.DelayTaskMapper;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chenmingzhe
 * @since 2020-04-23
 */
public class DelayTaskService extends ServiceImpl<DelayTaskMapper, DelayTask> {

    private Logger logger = LoggerFactory.getLogger(DelayTaskService.class);

    @Autowired
    private Scheduler scheduler;

    public void addDelayTaskToJob(Date plannedTime, String taskName) {
        logger.info("add taskJob {}",taskName);
        try {
            Trigger trigger = scheduler.getTrigger(TriggerKey.triggerKey(Constants.DELAY_JOB_TRIGGER_KEY));
            if (trigger == null || plannedTime.before(trigger.getStartTime())) {
                setJobTrigger(plannedTime,taskName);
            }
        } catch (Exception e) {
            logger.error( taskName + "job create error {}", e);
        }
    }

    private void setJobTrigger(Date startTime, String taskName) throws Exception {
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(Constants.DELAY_JOB_TRIGGER_KEY)
                .startAt(startTime)
                .build();
        JobDetail jobDetail = scheduler.getJobDetail(new JobKey(Constants.DELAY_JOB_KEY
               ));
        if (jobDetail == null) {
            jobDetail = JobBuilder.newJob(DelayTaskJob.class)
                    .withIdentity(Constants.DELAY_JOB_KEY)
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            scheduler.rescheduleJob(trigger.getKey(), trigger);
        }
    }
}
