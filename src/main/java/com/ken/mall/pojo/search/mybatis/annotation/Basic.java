package com.ken.mall.pojo.search.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * com.xfbetter.service.pojo.search.mybatis.annotation
 * author Daniel
 * 2018/1/15.
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Basic {
    String value() default "";
    boolean exclude() default false;
}
