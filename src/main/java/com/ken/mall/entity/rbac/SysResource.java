package com.ken.mall.entity.rbac;


import com.ken.mall.entity.CascadeEntityAbstract;
import com.ken.mall.entity.rbac.enums.ResourceType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Ken
 * @date 2019/4/24
 * @description
 */
@Entity
@Table(name = "sys_resource")
@Getter
@Setter
public class SysResource extends CascadeEntityAbstract {

    @Column(name = "type", columnDefinition="varchar(32) not null comment '资源类型'")
    @Enumerated(EnumType.STRING)
    private ResourceType type = ResourceType.menu;

    @Column(name = "permission", columnDefinition="varchar(255) not null comment '权限码'")
    private String permission;

}
