package com.ken.mall.entity.goods;

import com.ken.mall.entity.AbstractOrderEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Ken
 * @date 2019/7/28
 * @description
 */
@Entity
@Table(name = "goods")
@Getter
@Setter
public class Goods extends AbstractOrderEntity {

    @Column(name = "goods_sn", columnDefinition="varchar(30) default null comment '商品编码'")
    private String goodsSn;

    @Column(name = "goods_name", columnDefinition="varchar(256) not null comment '商品名称'")
    private String goodsName;

    @Column(name = "category_id", columnDefinition="bigint(20) not null comment '分类id'")
    private Long categoryId;

    @Column(name = "brand_id", columnDefinition="bigint(20) default '0' comment '品牌id'")
    private Long brandId;

    @Column(name = "goods_desc", columnDefinition="text comment '商品详情'")
    private String goodsDesc;

    @Column(name = "primary_pic_url", columnDefinition="varchar(255) not null comment '主图'")
    private String primaryPicUrl;

    @Column(name = "goods_unit", columnDefinition="varchar(16) default null comment '单位'")
    private String goodsUnit;

    @Column(name = "market_price", columnDefinition="decimal(10,2) default '0.00' comment '市场价'")
    private BigDecimal marketPrice;

    @Column(name = "sale_price", columnDefinition="decimal(10,2) default '0.00' comment '销售价'")
    private BigDecimal salePrice;

    @Column(name = "sesame_price", columnDefinition="decimal(10,2) default '0.00' comment '芝麻价格'")
    private BigDecimal sesamePrice;

    @Column(name = "price_unit", columnDefinition="varchar(16) default null comment '价格单位'")
    private String priceUnit;

    @Column(name = "sales", columnDefinition="int(11) unsigned default '0' comment '销量'")
    private Long sales;

    @Column(name = "is_hot", columnDefinition="tinyint(1) unsigned default '0' comment '是否热销'")
    private boolean isHot;

    @Column(name = "is_on_sale", columnDefinition="tinyint(1) unsigned default '1' comment '是否上架'")
    private boolean isOnSale;
}
