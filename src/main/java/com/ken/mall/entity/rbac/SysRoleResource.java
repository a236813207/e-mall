package com.ken.mall.entity.rbac;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Ken
 * @date 2019/4/24
 * @description
 */
@Entity
@Table(name = "mk_role_resource")
@IdClass(SysRoleResourcePK.class)
public class SysRoleResource implements Serializable{
    private Long roleId;
    private Long resourceId;

    @Id
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

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
        SysRoleResource that = (SysRoleResource) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(resourceId, that.resourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, resourceId);
    }
}
