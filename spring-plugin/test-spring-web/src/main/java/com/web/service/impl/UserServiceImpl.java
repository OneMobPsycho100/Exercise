package com.web.service.impl;

import com.web.module.User;
import com.web.service.UserService;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/30 11:58
 */
public class UserServiceImpl implements UserService {

    @Override
    public User getUserByName(String name) {
        return new User(name, "man", 18);
    }
}
