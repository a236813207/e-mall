package com.ken.mall.pojo.base;

/**
 * @author Ken
 * @date 2019/7/28
 * @description key value对形式枚举基类
 */
public interface PairsEnum<E extends Enum<E>> {
    int getValue();
    String getKey();
}
