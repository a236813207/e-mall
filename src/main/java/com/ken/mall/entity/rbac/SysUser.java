package com.ken.mall.entity.rbac;

import com.ken.mall.entity.BaseEntity;
import com.ken.mall.entity.rbac.converter.SysUserStatusEnumConverter;
import com.ken.mall.entity.rbac.enums.SysUserStatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ken
 * @date 2019/4/24
 * @description
 */
@Entity
@Table(name = "sys_user")
@Getter
@Setter
public class SysUser extends BaseEntity {

    @Column(name = "user_name", columnDefinition="varchar(32) not null comment '账户名'")
    private String userName;

    @Column(name = "password", columnDefinition="varchar(128) not null comment '密码'")
    private String password;

    @Column(name = "salt", columnDefinition="varchar(128) not null comment '密码盐值'")
    private String salt;

    @Column(name = "status", columnDefinition="tinyint(2) not null comment '状态'")
    @Convert(converter = SysUserStatusEnumConverter.class)
    private SysUserStatusEnum status;
}
