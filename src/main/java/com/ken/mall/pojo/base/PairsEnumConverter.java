package com.ken.mall.pojo.base;

/**
 * com.xfbetter.service.converter
 * author Daniel
 * 2017/12/14.
 */
public abstract class PairsEnumConverter<E extends PairsEnum> {

    protected Integer convertToDatabaseColumnValue(E e) {
        if (e == null) {
            return null;
        }
        return e.getValue();
    }

    protected E convertToAttributeValue(Class<E> clazz, Integer value) {
        if (value == null) {
            return null;
        }
        E[] result = clazz.getEnumConstants();
        for (E t : result) {
            if (t.getValue() == value) {
                return t;
            }
        }
        return null;
    }
}
