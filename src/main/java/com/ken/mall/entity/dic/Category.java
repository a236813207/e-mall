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
@Table(name = "dic_category")
@Getter
@Setter
public class Category extends AbstractOrderEntity {

    @Column(name = "icon", columnDefinition="varchar(255) default null comment 'icon'")
    private String icon;

    @Column(name = "name", columnDefinition="varchar(32) not null comment '分类名称'")
    private String name;

    @Column(name = "parent_id", columnDefinition="bigint(20) not null comment '父类id'")
    private Long parentId;

    @Column(name = "parent_ids", columnDefinition="varchar(512) not null comment '父类id集合'")
    private String parentIds;

    @Column(name = "url", columnDefinition="varchar(255) default null comment '跳转url'")
    private String url;

}
