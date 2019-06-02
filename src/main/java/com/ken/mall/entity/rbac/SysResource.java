package com.ken.mall.entity.rbac;


import com.ken.mall.entity.CascadeEntity;
import com.ken.mall.entity.rbac.enum_.ResourceType;
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
public class SysResource extends CascadeEntity {
    //资源类型
    @Column(nullable = false, length = 16)
    @Enumerated(EnumType.STRING)
    private ResourceType type = ResourceType.menu;
    //权限标识符
    @Column(nullable = false, length = 32)
    private String permission;

}
