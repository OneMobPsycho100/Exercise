package com.schedule.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/24 18:10
 */
@Retention(RUNTIME)
@Target(TYPE)
@Import()
public @interface EnableSchedule {

}
