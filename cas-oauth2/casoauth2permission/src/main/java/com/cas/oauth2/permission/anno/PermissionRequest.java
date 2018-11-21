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
public @interface PermissionRequest {
    enum Permission {
        None(0),
        Inherited (-1),
        Read (1),
        New (2),
        Update (4),
        Delete (8);

        private int value;
        Permission(int v) {
            this.value = v;
        }

        public int getValue() {
            return value;
        }
    };

    Permission[] value() default { Permission.Inherited };
}
