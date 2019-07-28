package com.ken.mall.entity.rbac.converter;

import com.ken.mall.entity.rbac.enums.SysUserStatusEnum;
import com.ken.mall.pojo.base.PairsEnumConverter;

import javax.persistence.AttributeConverter;

public class SysUserStatusEnumConverter extends PairsEnumConverter<SysUserStatusEnum>
        implements AttributeConverter<SysUserStatusEnum,Integer> {

    @Override
    public Integer convertToDatabaseColumn(SysUserStatusEnum attribute) {
        return super.convertToDatabaseColumnValue(attribute);
    }

    @Override
    public SysUserStatusEnum convertToEntityAttribute(Integer dbData) {
        return super.convertToAttributeValue(SysUserStatusEnum.class, dbData);
    }
}
