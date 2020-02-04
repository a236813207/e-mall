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
@Table(name = "dic_attribute_category")
@Getter
@Setter
public class AttributeCategory extends AbstractOrderEntity {
    
    @Column(name = "name", columnDefinition="varchar(32) not null comment '属性分类名称'")
    private String name;

    @Column(name = "is_enabled", columnDefinition="tinyint(1) not null comment '是否可用'")
    private Boolean isEnabled;

}
