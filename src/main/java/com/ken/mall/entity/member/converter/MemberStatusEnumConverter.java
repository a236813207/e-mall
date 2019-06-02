package com.ken.mall.entity.member.converter;

import com.ken.mall.entity.member.enums.MemberStatusEnum;
import com.ken.mall.pojo.base.PairsEnumConverter;

import javax.persistence.AttributeConverter;

public class MemberStatusEnumConverter extends PairsEnumConverter<MemberStatusEnum>
        implements AttributeConverter<MemberStatusEnum,Integer> {

    @Override
    public Integer convertToDatabaseColumn(MemberStatusEnum attribute) {
        return super.convertToDatabaseColumnValue(attribute);
    }

    @Override
    public MemberStatusEnum convertToEntityAttribute(Integer dbData) {
        return super.convertToAttributeValue(MemberStatusEnum.class, dbData);
    }
}
