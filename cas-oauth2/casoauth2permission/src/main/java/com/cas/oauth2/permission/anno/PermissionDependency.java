package com.cas.oauth2.permission.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xiemingquan on 2016/12/21.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionDependency {
    //    String value();
//    PermissionRequest.Permission permission();
    String[] value();
}
