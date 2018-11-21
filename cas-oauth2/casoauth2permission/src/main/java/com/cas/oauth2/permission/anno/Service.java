package com.cas.oauth2.permission.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Retention表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效）
 *  RetentionPoicy取值
 *         1.SOURCE:在源文件中有效（即源文件保留）
 *         2.CLASS:在class文件中有效（即class保留）
 *         3.RUNTIME:在运行时有效（即运行时保留）
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
    String value() default "";
}
