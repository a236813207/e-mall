package com.ken.mall.entity.rbac;


import com.ken.mall.entity.CascadeEntity;
import com.ken.mall.entity.rbac.enum_.ResourceType;

import javax.persistence.*;

/**
 * @author Ken
 * @date 2019/4/24
 * @description
 */
@Entity
@Table(name = "mk_resource")
public class SysResource extends CascadeEntity {
    //资源类型
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ResourceType type = ResourceType.menu;
    //权限标识符
    @Column(nullable = false)
    private String permission;

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
