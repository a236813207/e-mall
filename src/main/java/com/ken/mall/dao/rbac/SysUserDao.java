package com.ken.mall.dao.rbac;

import com.ken.mall.dao.GenericRepository;
import com.ken.mall.entity.rbac.SysUser;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
public interface SysUserDao extends GenericRepository<SysUser,Integer> {
    SysUser findByUserName(String username);
}
