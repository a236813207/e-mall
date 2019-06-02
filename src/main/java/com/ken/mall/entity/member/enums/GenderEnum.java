package com.ken.mall.entity.member.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ken.mall.pojo.base.PairsEnum;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GenderEnum implements PairsEnum<GenderEnum> {

    MALE(1, "男"), FEMALE(2, "女"), SECRET(3, "保密");
    private int value;
    private String key;

    GenderEnum(int value, String key) {
        this.value = value;
        this.key = key;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getKey() {
        return key;
    }
}
