package com.ken.mall.entity.order.converter;

import com.ken.mall.entity.order.enums.OrderLogStatusEnum;
import com.ken.mall.pojo.base.PairsEnumConverter;

import javax.persistence.AttributeConverter;

public class OrderLogStatusEnumConverter extends PairsEnumConverter<OrderLogStatusEnum>
        implements AttributeConverter<OrderLogStatusEnum,Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderLogStatusEnum attribute) {
        return super.convertToDatabaseColumnValue(attribute);
    }

    @Override
    public OrderLogStatusEnum convertToEntityAttribute(Integer dbData) {
        return super.convertToAttributeValue(OrderLogStatusEnum.class, dbData);
    }
}
