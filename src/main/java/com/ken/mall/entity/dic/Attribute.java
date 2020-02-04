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
@Table(name = "dic_attribute")
@Getter
@Setter
public class Attribute extends AbstractOrderEntity {

    @Column(name = "attribute_catgory_id", columnDefinition="bigint(20) not null comment '属性分类id'")
    private String attributeCatgoryId;

    @Column(name = "name", columnDefinition="varchar(32) not null comment '属性名称'")
    private String name;

    @Column(name = "value", columnDefinition="varchar(255) not null comment '属性值'")
    private String value;

}
