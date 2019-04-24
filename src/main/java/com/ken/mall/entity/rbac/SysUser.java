package com.ken.mall.entity.rbac;

import com.ken.mall.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ken
 * @date 2019/4/24
 * @description
 */
@Entity
@Table(name = "mk_user")
public class SysUser extends AbstractEntity<Integer> {
    @Id
    @GeneratedValue
    private Integer id;
    private String userName;
    private String password;
    private String salt;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }


    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }
}
