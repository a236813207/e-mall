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
@Table(name = "dic_specification")
@Getter
@Setter
public class Specification extends AbstractOrderEntity {

    @Column(name = "name", columnDefinition="varchar(32) not null comment '规格名称'")
    private String name;

}
