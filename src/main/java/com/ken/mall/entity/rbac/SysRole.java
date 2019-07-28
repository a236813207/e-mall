package com.ken.mall.entity.rbac;

import com.ken.mall.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ken
 * @date 2019/4/24
 * @description
 */
@Entity
@Table(name = "sys_role")
@Getter
@Setter
public class SysRole extends BaseEntity {

    @Column(name = "name", columnDefinition="varchar(32) not null comment '角色名称'")
    private String name;

    @Column(name = "memo", columnDefinition="varchar(128) not null comment '描述'")
    private String memo;

}
