package com.ken.mall.entity.activity.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ken.mall.entity.member.enums.GenderEnum;
import com.ken.mall.pojo.base.PairsEnum;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RushPurchaseStatusEnum implements PairsEnum<GenderEnum> {

    UNSTART(1, "未开始"),
    UNDERWAY(2, "进行中"),
    LOTTERY(3, "已开奖"),
    ENDED(4, "已结束");

    private int value;
    private String key;

    RushPurchaseStatusEnum(int value, String key) {
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
