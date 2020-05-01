package com.cmz.service;

import com.cmz.module.User;
import org.springframework.stereotype.Service;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/30 11:57
 */
@Service
public interface UserService {

    User getUserByName(String name);
}
