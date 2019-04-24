package com.ken.mall.mapper.rbac;

import com.ken.mall.entity.rbac.SysResource;
import com.ken.mall.entity.rbac.SysRole;
import com.ken.mall.entity.rbac.SysUser;

import java.util.List;
import java.util.Map;

/**
 * RBAC相关查询
 * com.xfbetter.service.mapper.rbac
 * author Daniel
 * 2017/12/19.
 */
public interface PrivilegeMapper {
    /**
     * 后台管理员查询
     */
    List<SysUser>  findAllManagers(Map<String, Object> param);

    /**
     * 查询用户的所有角色
     */
    List<SysRole> findAllRolesByUserId(Integer userId);

    /**
     * 查询用户的资源权限
     */
    List<SysResource> findPermissionsByUserId(Integer userId);

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
