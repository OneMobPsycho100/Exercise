package com.cmz.plugin.exception;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/29 17:44
 */
public class PluginException extends RuntimeException {

    public PluginException() {
        super();
    }

    public PluginException(String message) {
        super(message);
    }

    public PluginException(String message, Throwable cause) {
        super(message, cause);
    }
}
