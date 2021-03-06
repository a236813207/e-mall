package com.ken.mall.entity.order.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ken.mall.pojo.base.PairsEnum;

/**
 * @author Ken
 * @date 2018/11/02
 * @description
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OrderStatusEnum implements PairsEnum<OrderStatusEnum> {
    /**
     * 订单状态
     */
    UN_FINISHED(1, "未完成"), SHIPPED(2, "已发货"), FINISHED(3, "已完成"), CANCELED(4, "已取消");

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
