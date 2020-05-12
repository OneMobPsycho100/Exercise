package com.cmz.sec.userdetails;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/12 22:00
 */
public interface UserDetailService {

    UserDetails loadUserByUsername(String username);
}
