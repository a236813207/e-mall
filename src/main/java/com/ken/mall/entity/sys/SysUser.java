package com.ken.mall.entity.sys;

import com.ken.mall.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
@Entity
@Table(name = "sys_user")
@Getter
@Setter
public class SysUser extends AbstractEntity<Integer> {

    @Id
    @GeneratedValue
    private Integer id;
    private String userName;
    private String password;
    private String salt;

}
