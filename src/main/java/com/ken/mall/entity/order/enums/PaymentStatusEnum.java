package com.ken.mall.entity.order.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ken.mall.pojo.base.PairsEnum;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PaymentStatusEnum implements PairsEnum<PaymentStatusEnum> {

    WAIT(1, "待付款"), PAID(2, "已付款"), REFUND(3, "退款");
    private int value;
    private String key;

    PaymentStatusEnum(int value, String key) {
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
