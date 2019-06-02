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

    @Column(unique = true, nullable = false, length = 32)
    private String role;

    @Column(nullable = false, length = 32)
    private String description;

}
