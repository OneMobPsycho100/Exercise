package com.cmz.sec.userdetails;

import java.io.Serializable;
import java.util.Set;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/12 21:42
 */
public class UserDetails implements Serializable {

    private String password;
    private final String username;
    private final Set<String> authorities;
    private final boolean enabled;


    public UserDetails(String password, String username, Set<String> authorities, boolean enabled) {
        if (username == null || "".equals(username) || password == null) {
            throw new IllegalArgumentException(
                    "Cannot pass null or empty values to constructor");
        }

        this.password = password;
        this.username = username;
        this.authorities = authorities;
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
