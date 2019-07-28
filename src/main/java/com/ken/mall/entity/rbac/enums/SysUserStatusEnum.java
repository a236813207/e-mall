package com.ken.mall.entity.rbac.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ken.mall.pojo.base.PairsEnum;

/**
 * @author Ken
 * @date 2019/7/28
 * @description
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SysUserStatusEnum implements PairsEnum<SysUserStatusEnum> {
    /**
     * 后台帐号状态
     */
    NORMAL(0, "正常"), DISABLE(1, "停用");
    private int value;
    private String key;

    SysUserStatusEnum(int value, String key) {
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
