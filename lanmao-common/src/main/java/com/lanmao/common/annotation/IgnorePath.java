package com.lanmao.common.annotation;

import java.lang.annotation.ElementType;

@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE,ElementType.PARAMETER})
public @interface IgnorePath {
}
