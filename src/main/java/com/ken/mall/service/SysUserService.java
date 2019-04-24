package com.ken.mall.service;

import com.ken.mall.entity.rbac.SysResource;
import com.ken.mall.entity.rbac.SysRole;
import com.ken.mall.entity.rbac.SysUser;
import com.ken.mall.pojo.exception.BizException;
import com.ken.mall.pojo.search.PageBo;
import com.ken.mall.pojo.search.PageRequestBo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
public interface SysUserService extends BaseService<SysUser, Integer> {

    String PASSWORD = "123456";

    /**
     * 根据用户名查找用户
     * @param username    用户名
     * @return         管理员
     * @throws BizException    业务异常
     */
    SysUser findByUsername(String username) throws BizException;

    /**
     * 获取用户角色列表
     * @param username    用户名
     * @return            用户角色列表
     * @throws BizException      业务异常
     */
    List<SysRole> findRoles(String username) throws BizException;

    /**
     * 获取用户的角色列表
     * @return        用户角色标识集合
     * @throws BizException   业务异常
     */
    Set<String> findRoleNames(String username) throws BizException;

    /**
     * 获取用户资源列表
     * @param username   用户名
     * @return     用户资源列表
     */
    List<SysResource> findResources(String username);

    /**
     * 获取用户的权限列表
     * @param username    用户名
     * @return        用户资源标识集合
     * @throws BizException    业务异常
     */
    Set<String> findPermissions(String username) throws BizException;

    /**
     * 查询管理员列表
     * @return     带分页信息的管理员列表
     */
    PageBo<SysUser> findPage(String nameKey, Integer roleId, PageRequestBo page);

    /**
     * 查询管理员列表（不分页，导出使用）
     */
    List<SysUser> findList(String nameKey, Integer roleId);

    /**
     * 委任管理员角色
     * @param managerId   用户ID
     * @param roleIds     角色Id
     */
    void assignRoles(Integer managerId, ArrayList<Long> roleIds) throws BizException;

    /**
     * 创建新管理员
     * @param sysUser  内容
     * @param roleId
     * @throws BizException
     */
    void createNew(SysUser sysUser, Long roleId) throws BizException;

    /**
     * 重置密码
     * @param id  用户ID
     * @throws BizException
     */
    void resetPassword(Integer id) throws  BizException;

    void update(SysUser sysUser, Long roleId) throws BizException;
}
