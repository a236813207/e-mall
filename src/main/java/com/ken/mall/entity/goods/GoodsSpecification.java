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
@Table(name = "goods_specification")
@Getter
@Setter
public class GoodsSpecification extends AbstractOrderEntity {

    @Column(name = "goods_id", columnDefinition="bigint(20) not null comment '商品id'")
    private Long goodsId;

    @Column(name = "spec_id", columnDefinition="bigint(20) not null comment '规格id'")
    private Long specId;

    @Column(name = "value", columnDefinition="varchar(32) not null comment '规格名称'")
    private String value;

    @Column(name = "pic_url", columnDefinition="varchar(256) default null comment '规格图片'")
    private String picUrl;

    @Column(name = "is_on_sale", columnDefinition="tinyint(1) unsigned default '1' comment '是否上架'")
    private boolean isOnSale;
}
