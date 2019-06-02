package com.ken.mall.entity.member.converter;

import com.ken.mall.entity.member.enums.GenderEnum;
import com.ken.mall.pojo.base.PairsEnumConverter;

import javax.persistence.AttributeConverter;

public class GenderEnumConverter extends PairsEnumConverter<GenderEnum>
        implements AttributeConverter<GenderEnum,Integer> {

    @Override
    public Integer convertToDatabaseColumn(GenderEnum attribute) {
        return super.convertToDatabaseColumnValue(attribute);
    }

    @Override
    public GenderEnum convertToEntityAttribute(Integer dbData) {
        return super.convertToAttributeValue(GenderEnum.class, dbData);
    }
}
