package com.ken.mall.entity.rbac;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Ken
 * @date 2019/4/24
 * @description
 */
public class SysUserRolePK implements Serializable {
    private Integer userId;
    private Long roleId;

    public SysUserRolePK() {
    }

    public SysUserRolePK(Integer userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    @Column(nullable = false, insertable = true, updatable = true)
    @Id
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(nullable = false, insertable = true, updatable = true)
    @Id
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserRolePK that = (SysUserRolePK) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
}
