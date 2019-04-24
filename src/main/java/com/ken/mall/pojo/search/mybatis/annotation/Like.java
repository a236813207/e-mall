package com.ken.mall.pojo.search.mybatis.annotation;


import com.ken.mall.pojo.search.mybatis.LikePattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Like {
    LikePattern pattern() default LikePattern.both;
}
