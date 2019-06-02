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
@Table(name = "sys_user_role")
@IdClass(SysUserRolePK.class)
@Getter
@Setter
public class SysUserRole implements Serializable{
    @Id
    @Column(name = "user_id", nullable = false, length = 20)
    private Long userId;

    @Id
    @Column(name = "role_id", nullable = false, length = 20)
    private Long roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserRole that = (SysUserRole) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
}
