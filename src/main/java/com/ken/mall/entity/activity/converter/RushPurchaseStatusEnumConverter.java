package com.ken.mall.entity.activity.converter;

import com.ken.mall.entity.activity.enums.RushPurchaseStatusEnum;
import com.ken.mall.pojo.base.PairsEnumConverter;

import javax.persistence.AttributeConverter;

public class RushPurchaseStatusEnumConverter extends PairsEnumConverter<RushPurchaseStatusEnum>
        implements AttributeConverter<RushPurchaseStatusEnum,Integer> {

    @Override
    public Integer convertToDatabaseColumn(RushPurchaseStatusEnum attribute) {
        return super.convertToDatabaseColumnValue(attribute);
    }

    @Override
    public RushPurchaseStatusEnum convertToEntityAttribute(Integer dbData) {
        return super.convertToAttributeValue(RushPurchaseStatusEnum.class, dbData);
    }
}
