package com.ken.mall.pojo.activity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Ken
 * @date 2020/3/29
 * @description
 */
@Data
public class RushPurchaseVo {

    private String goodsName;
    private BigDecimal salePrice;
    private BigDecimal sesamePrice;
    private String activitySn;
    private Integer sesame;
    private Integer limits;
    private Integer requiredParticipants;
    private Integer hasParticipants;
    private Integer participateNums;

}
