package com.cmz.sec.context;

import com.cmz.sec.userdetails.UserDetails;

/**
 * 储存当前用户信息
 * @Author: chenmingzhe
 * @Date: 2020/5/12 22:26
 */
public class MySecurityContextHolder {

    private static final ThreadLocal<UserDetails> CONTEXT_HOLDER = new ThreadLocal<>();

    public static UserDetails getUserContext() {
        return CONTEXT_HOLDER.get();
    }

    public static void setUserContext(UserDetails userDetails) {
        CONTEXT_HOLDER.set(userDetails);
    }

    public static void clearContext() {
        CONTEXT_HOLDER.remove();
    }
}
