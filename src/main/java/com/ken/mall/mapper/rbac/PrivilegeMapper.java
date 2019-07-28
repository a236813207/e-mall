package com.ken.mall.mapper.rbac;

import com.ken.mall.entity.rbac.SysResource;
import com.ken.mall.entity.rbac.SysRole;
import com.ken.mall.entity.rbac.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
public interface PrivilegeMapper {
    /**
     * 后台管理员查询
     */
    List<SysUser> findAllManagers(Map<String, Object> param);

    /**
     * 查询用户的所有角色
     */
    List<SysRole> findAllRolesByUserId(Long userId);

    /**
     * 查询用户的资源权限
     */
    List<SysResource> findPermissionsByUserId(Long userId);

    /**
     * 查询角色的资源权限
     */
    List<SysResource> findRolePermissionsByRoleId(Long roleId);

    /**
     * 根据权限查金谷角色集合
     * @param permission
     * @return
     */
    List<Map<String, Object>> findJinguRolesByPermission(String permission);
}
