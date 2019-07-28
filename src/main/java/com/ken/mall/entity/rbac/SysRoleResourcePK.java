package com.ken.mall.entity.rbac;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Ken
 * @date 2019/4/24
 * @description
 */
@Getter
@Setter
public class SysRoleResourcePK implements Serializable {
    @Id
    @Column(nullable = false, insertable = true, updatable = true)
    private Long roleId;

    @Id
    @Column(nullable = false, insertable = true, updatable = true)
    private Long resourceId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysRoleResourcePK that = (SysRoleResourcePK) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(resourceId, that.resourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, resourceId);
    }
}
