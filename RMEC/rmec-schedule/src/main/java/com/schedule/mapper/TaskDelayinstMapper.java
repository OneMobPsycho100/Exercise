package com.schedule.mapper;

import com.schedule.entity.DelayinstWorkInfo;
import com.schedule.entity.TaskDelayinst;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenmingzhe
 * @since 2020-04-24
 */
public interface TaskDelayinstMapper extends  BaseMapper<TaskDelayinst> {

    /**
     * 根据task状态和开始时间
     * 查询具体的实例信息
     * @param status
     * @param startTime
     * @return
     */
    List<DelayinstWorkInfo> queryInstByStatusAndTime(
            @Param("status") String status, @Param("startTime") String startTime);


}
