package com.ken.mall.entity.rbac;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Ken
 * @date 2019/4/24
 * @description
 */
@Entity
@Table(name = "sys_role_resource")
@IdClass(SysRoleResourcePK.class)
@Getter
@Setter
public class SysRoleResource implements Serializable{

    @Id
    @Column(name = "role_id", nullable = false, length = 20)
    private Long roleId;

    @Id
    @Column(name = "resource_id", nullable = false, length = 20)
    private Long resourceId;

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
