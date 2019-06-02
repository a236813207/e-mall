package com.ken.mall.entity.member.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ken.mall.pojo.base.PairsEnum;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MemberStatusEnum implements PairsEnum<MemberStatusEnum> {

    NORMAL(1, "正常"), DISABLE(2, "停用");
    private int value;
    private String key;

    MemberStatusEnum(int value, String key) {
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
