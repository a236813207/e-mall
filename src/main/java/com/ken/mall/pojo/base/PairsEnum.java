package com.ken.mall.pojo.base;

/**
 * key value对形式枚举基类
 * @param <E>  枚举类型
 */
public interface PairsEnum<E extends Enum<E>> {
    int getValue();
    String getKey();
}
