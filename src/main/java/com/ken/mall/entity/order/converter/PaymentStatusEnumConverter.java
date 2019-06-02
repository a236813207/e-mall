package com.ken.mall.entity.order.converter;

import com.ken.mall.entity.order.enums.PaymentStatusEnum;
import com.ken.mall.pojo.base.PairsEnumConverter;

import javax.persistence.AttributeConverter;

public class PaymentStatusEnumConverter extends PairsEnumConverter<PaymentStatusEnum>
        implements AttributeConverter<PaymentStatusEnum,Integer> {

    @Override
    public Integer convertToDatabaseColumn(PaymentStatusEnum attribute) {
        return super.convertToDatabaseColumnValue(attribute);
    }

    @Override
    public PaymentStatusEnum convertToEntityAttribute(Integer dbData) {
        return super.convertToAttributeValue(PaymentStatusEnum.class, dbData);
    }
}
