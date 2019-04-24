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
public class SysRoleResourcePK implements Serializable {
    private Long roleId;
    private Long resourceId;

    @Column(nullable = false, insertable = true, updatable = true)
    @Id
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Column(nullable = false, insertable = true, updatable = true)
    @Id
    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRoleResourcePK that = (SysRoleResourcePK) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(resourceId, that.resourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, resourceId);
    }
}
