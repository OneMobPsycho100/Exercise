package com.web.service;

import com.web.module.User;
import org.springframework.stereotype.Component;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/30 11:57
 */
public interface UserService {

    User getUserByName(String name);
}
