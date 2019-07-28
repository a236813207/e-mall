package com.ken.mall.entity.order.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ken.mall.pojo.base.PairsEnum;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OrderLogStatusEnum implements PairsEnum<OrderLogStatusEnum> {

    CREATED(1, "订单创建"),
    PAID(2, "订单支付"),
    SHIPPED(3, "订单发货"),
    RECEIVED(4, "确认收货"),
    COMPLETED(5, "订单完成"),
    CANCELED(6, "订单取消"),
    REFUNDED(7, "订单退款"),
    OTHER(8, "其它");

    private int value;
    private String key;

    OrderLogStatusEnum(int value, String key) {
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
