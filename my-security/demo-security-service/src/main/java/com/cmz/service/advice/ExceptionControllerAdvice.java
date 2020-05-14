package com.cmz.service.advice;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/13 11:55
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    /**
     * @param ex
     * @param req
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(code = HttpStatus.OK)
    public String errorHandler(Exception ex, ServletWebRequest req) {
        Map<String, String[]> map = req.getParameterMap();
        StringBuilder reqContext = new StringBuilder(req.getRequest().getRequestURI());
        map.forEach((k, v) -> {
            reqContext.append(" ").append(k).append(":").append(StringUtils.join(v));
        });
        LOG.error(reqContext.toString(), ex);
        return ex.getMessage();
    }
}