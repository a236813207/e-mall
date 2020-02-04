package com.ken.mall.entity.dic;

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
@Table(name = "dic_brand")
@Getter
@Setter
public class Brand extends AbstractOrderEntity {

    @Column(name = "icon", columnDefinition="varchar(255) default null comment 'icon'")
    private String icon;

    @Column(name = "name", columnDefinition="varchar(32) not null comment '品牌名称'")
    private String name;

    @Column(name = "url", columnDefinition="varchar(255) default null comment '跳转url'")
    private String url;

}
