package com.ken.mall.web.mall.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ken
 * @date 2019/4/24
 * @description 对接口进行权限控制
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenAuth {
    //不需要验证时使用
    boolean require() default true;
}
