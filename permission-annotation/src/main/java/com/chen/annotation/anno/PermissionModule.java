package com.chen.annotation.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface PermissionModule {
    String id();

    String name();

    String parent() default "";

    PermissionDependency[] dependencies()

            default {
    }

            ;
}


