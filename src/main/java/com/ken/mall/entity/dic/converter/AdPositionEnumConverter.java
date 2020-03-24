package com.ken.mall.entity.dic.converter;

import com.ken.mall.entity.dic.enums.AdPositionEnum;
import com.ken.mall.pojo.base.PairsEnumConverter;

import javax.persistence.AttributeConverter;

public class AdPositionEnumConverter extends PairsEnumConverter<AdPositionEnum>
        implements AttributeConverter<AdPositionEnum,Integer> {

    @Override
    public Integer convertToDatabaseColumn(AdPositionEnum attribute) {
        return super.convertToDatabaseColumnValue(attribute);
    }

    @Override
    public AdPositionEnum convertToEntityAttribute(Integer dbData) {
        return super.convertToAttributeValue(AdPositionEnum.class, dbData);
    }
}
