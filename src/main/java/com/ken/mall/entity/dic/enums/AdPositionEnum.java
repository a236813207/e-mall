package com.ken.mall.entity.dic.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ken.mall.pojo.base.PairsEnum;

/**
 * @author Ken
 * @date 2020/3/21
 * @description
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AdPositionEnum implements PairsEnum<AdPositionEnum> {
    /**
     * 首页顶部轮播图
     */
    INDEX_TOP(1, "首页顶部轮播图"),
    /**
     * 首页中间文字
     */
    INDEX_MIDDLE(2, "首页中间文字");

    private int value;
    private String key;

    AdPositionEnum(int value, String key) {
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
