package com.ken.mall.entity.rbac;

import com.ken.mall.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ken
 * @date 2019/4/24
 * @description
 */
@Entity
@Table(name = "mk_role")
public class SysRole extends BaseEntity {

    private String role;

    private String description;

    @Column(unique = true)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
