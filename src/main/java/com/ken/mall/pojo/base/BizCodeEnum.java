package com.ken.mall.pojo.base;

/**
 * 异常形式枚举基类
 * @param <E>  枚举类型
 */
public interface BizCodeEnum<E extends Enum<E>> {
    int getCode();
    String getMessage();
    //起始值
    int getStart();
}
