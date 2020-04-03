package com.ken.mall.pojo.shop;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Ken
 * @date 2020/3/29
 * @description
 */
@Data
public class GoodsListVo {

    private Long id;
    private String primaryPicUrl;
    private String goodsName;
    private String priceUnit;
    private BigDecimal salePrice;
    private BigDecimal sesamePrice;

}
