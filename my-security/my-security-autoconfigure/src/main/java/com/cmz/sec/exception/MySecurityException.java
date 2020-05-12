package com.cmz.sec.exception;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/12 23:06
 */
public class MySecurityException extends RuntimeException {

    public MySecurityException() {
        super();
    }

    public MySecurityException(String message) {
        super(message);
    }

    public MySecurityException(String message, Throwable cause) {
        super(message, cause);
    }
}
