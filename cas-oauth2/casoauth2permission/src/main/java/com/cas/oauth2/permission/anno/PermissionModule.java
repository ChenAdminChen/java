package com.cas.oauth2.permission.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by swm on 2016-12-11
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionModule {
    String id();
    String name();

    String parent() default "";

    PermissionDependency[] dependencies() default {};
}
