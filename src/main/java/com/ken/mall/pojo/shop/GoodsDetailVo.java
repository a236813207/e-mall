package com.ken.mall.pojo.shop;

import com.ken.mall.entity.goods.GoodsCarousel;
import com.ken.mall.entity.goods.GoodsSpecification;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ken
 * @date 2020/3/29
 * @description
 */
@Data
public class GoodsDetailVo {

    private Long id;
    private List<GoodsCarousel> carousels;
    private String goodsName;
    private String priceUnit;
    private BigDecimal salePrice;
    private BigDecimal sesamePrice;
    private List<GoodsSpecification> specifications;
    private String goodsDesc;

}
