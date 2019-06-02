package com.ken.mall.dao.rbac;

import com.ken.mall.dao.GenericRepository;
import com.ken.mall.entity.rbac.SysUserRole;
import com.ken.mall.entity.rbac.SysUserRolePK;

import java.util.List;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
public interface SysUserRoleDao extends GenericRepository<SysUserRole,SysUserRolePK> {
    /**
     * 删除用户的角色信息
     * @param userId    用户Id
     */
    void deleteByUserId(Long userId);

    List<SysUserRole> findByRoleId(Long roleId);
}
