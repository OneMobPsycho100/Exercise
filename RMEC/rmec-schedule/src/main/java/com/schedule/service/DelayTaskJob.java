package com.schedule.service;

import com.remc.common.Constants;
import com.remc.service.RabbitMQService;
import com.schedule.common.DelayInstStatusEnum;
import com.schedule.entity.DelayinstWorkInfo;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 延时任务Job
 * @Author: chenmingzhe
 * @Date: 2020/4/24 15:14
 */
public class DelayTaskJob extends QuartzJobBean {


    @Autowired
    private TaskDelayinstService delayInstService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitMQService rabbitMqService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Date startTime = context.getTrigger().getStartTime();
        // 查询出该时间点需要执行的实例信息
        List<DelayinstWorkInfo> delayinstWorkInfos = delayInstService
                .queryInstByStatusAndTime(DelayInstStatusEnum.NOT_RUN.getCode(), startTime)
                .stream()
                // 设置redis任务锁，防止多服务下任务被多次触发
                .filter(w -> this.isLock(w.getDelayworkid()))
                .peek(w -> {
                    delayInstService.updateStatusById(DelayInstStatusEnum.WAIT_RUN.getCode(),
                            w.getDelayworkid());
                    rabbitMqService.addNewQueue(Constants.QUEUE_TASK_DELAY,
                            Constants.ROUTERKEY_TASK_DELAY);
                })
                .collect(Collectors.toList());
        // 往mq发送消息 对具体的实例进行调用
        for (DelayinstWorkInfo work : delayinstWorkInfos) {
            rabbitMqService.sendMessage(Constants.ROUTERKEY_TASK_DELAY, work);
        }
    }

    private Boolean isLock(String delayWorkId) {
        return redisTemplate.opsForValue()
                .setIfAbsent("delay" + delayWorkId, delayWorkId, 10, TimeUnit.SECONDS);
    }


}
