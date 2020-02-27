package com.ken.mall.entity.goods;

import com.ken.mall.entity.AbstractOrderEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ken
 * @date 2019/7/28
 * @description
 */
@Entity
@Table(name = "goods_carousel")
@Getter
@Setter
public class GoodsCarousel extends AbstractOrderEntity {

    @Column(name = "goods_id", columnDefinition="bigint(20) not null comment '商品id'")
    private Long goodsId;

    @Column(name = "img_url", columnDefinition="varchar(256) not null comment '轮播图片'")
    private String imgUrl;

    @Column(name = "img_desc", columnDefinition="varchar(256) default null comment '图片描述'")
    private String imgDesc;
}
