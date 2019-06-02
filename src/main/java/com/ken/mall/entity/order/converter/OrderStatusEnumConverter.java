package com.ken.mall.entity.order.converter;

import com.ken.mall.entity.order.enums.OrderStatusEnum;
import com.ken.mall.pojo.base.PairsEnumConverter;

import javax.persistence.AttributeConverter;

public class OrderStatusEnumConverter extends PairsEnumConverter<OrderStatusEnum>
        implements AttributeConverter<OrderStatusEnum,Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderStatusEnum attribute) {
        return super.convertToDatabaseColumnValue(attribute);
    }

    @Override
    public OrderStatusEnum convertToEntityAttribute(Integer dbData) {
        return super.convertToAttributeValue(OrderStatusEnum.class, dbData);
    }
}
