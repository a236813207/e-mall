package com.ken.mall.entity.order.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ken.mall.pojo.base.PairsEnum;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OrderStatusEnum implements PairsEnum<OrderStatusEnum> {

    UN_FINISHED(1, "未完成"), FINISHED(2, "已完成"), CANCELD(3, "已取消");
    private int value;
    private String key;

    OrderStatusEnum(int value, String key) {
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
