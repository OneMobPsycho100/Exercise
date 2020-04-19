package com.msg.service;

import com.msg.entity.MessageInfo;
import com.msg.mapper.MessageMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenmingzhe
 * @since 2020-04-18
 */
@Service
public class MessageService extends  ServiceImpl<MessageMapper, MessageInfo> {

}
