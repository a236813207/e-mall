package com.ken.mall.service.iml;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.ken.mall.dao.rbac.SysUserDao;
import com.ken.mall.dao.rbac.SysUserRoleDao;
import com.ken.mall.entity.rbac.SysResource;
import com.ken.mall.entity.rbac.SysRole;
import com.ken.mall.entity.rbac.SysUser;
import com.ken.mall.entity.rbac.SysUserRole;
import com.ken.mall.mapper.rbac.PrivilegeMapper;
import com.ken.mall.pojo.exception.BizException;
import com.ken.mall.pojo.exception.codes.BizCodeFace;
import com.ken.mall.pojo.exception.codes.ErrorCode;
import com.ken.mall.pojo.search.PageBo;
import com.ken.mall.pojo.search.PageRequestBo;
import com.ken.mall.service.SysUserService;
import com.ken.mall.utils.password.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
@Service("sysUserServiceIml")
@Transactional
public class SysUserServiceIml extends BaseServiceIml<SysUser, Integer> implements SysUserService {

    @Autowired
    private SysUserRoleDao userRoleDao;

    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Override
    public SysUser findByUsername(String username) throws BizException {
        SysUserDao userDao = (SysUserDao) getRepository();
        return userDao.findByUserName(username);
    }

    @Override
    public List<SysRole> findRoles(String username) throws BizException {
        SysUser user = findByUsername(username);
        if (user == null) {
            return Collections.emptyList();
        }
        List<SysRole> roles = privilegeMapper.findAllRolesByUserId(user.getId());
        return roles;
    }

    @Override
    public Set<String> findRoleNames(String username) throws BizException {
        List<SysRole> roles = findRoles(username);
        return roles.stream().map(SysRole::getRole).collect(Collectors.toSet());
    }

    @Override
    public List<SysResource> findResources(String username) {
        SysUser manager = findByUsername(username);
        if (manager == null) {
            return Collections.emptyList();
        }
        return privilegeMapper.findPermissionsByUserId(manager.getId());
    }

    @Override
    public Set<String> findPermissions(String username) throws BizException {
        List<SysResource> resources = findResources(username);
        return resources.stream().map(SysResource::getPermission).collect(Collectors.toSet());
    }

    @Override
    public void delete(Integer integer) {
        this.userRoleDao.deleteByUserId(integer);
        super.delete(integer);
    }

    @Override
    public PageBo<SysUser> findPage(String nameKey, Integer roleId, PageRequestBo page) {
        Map<String, Object> param = new HashMap<>();
        if (nameKey != null) {
            param.put("userName", nameKey);
        }
        param.put("roleId", roleId);
        Page<SysUser> result = PageHelper.startPage(page.getPage(), page.getRows())
                .setOrderBy("id desc")
                .doSelectPage(() -> {
                    this.privilegeMapper.findAllManagers(param);
                });
        return new PageBo<>(result.getResult(), result.getTotal(), page);
    }

    @Override
    public List<SysUser> findList(String nameKey, Integer roleId) {
        Map<String, Object> param = new HashMap<>();
        if (nameKey != null) {
            param.put("username", "%" + nameKey + "%");
        }
        param.put("roleId", roleId);
        return this.privilegeMapper.findAllManagers(param);
    }

    @Override
    public void assignRoles(Integer managerId, ArrayList<Long> roleIds) throws BizException {
        //先清空原角色
        this.userRoleDao.deleteByUserId(managerId);
        //重新增加
        roleIds.forEach(item -> {
            SysUserRole role = new SysUserRole();
            role.setRoleId(Long.valueOf(item));
            role.setUserId(managerId);
            this.userRoleDao.save(role);
        });
    }


    @Override
    public void createNew(SysUser sysUser, Long roleId) throws BizException {
        String salt = PasswordHelper.generateSalt();
        String password = PasswordHelper.encodePassword(sysUser.getPassword(), salt);
        sysUser.setPassword(password);
        sysUser.setSalt(salt);
        this.save(sysUser);
        this.assignRoles(sysUser.getId(), Lists.newArrayList(roleId));
    }

    @Override
    public void resetPassword(Integer id) throws BizException {
        SysUser sysUser = this.find(id);
        if (sysUser == null) {
            throw new BizException(BizCodeFace.createBizCode(ErrorCode.DATE_NULL).message("未找到用户信息"));
        }
        String salt = PasswordHelper.generateSalt();
        String password = PasswordHelper.encodePassword(PASSWORD, salt);
        sysUser.setPassword(password);
        sysUser.setSalt(salt);
        this.update(sysUser);
    }

    @Override
    public void update(SysUser sysUser, Long roleId) throws BizException {
        String pwd = sysUser.getPassword();
        if (!StringUtils.isEmpty(pwd)) {
            String salt = PasswordHelper.generateSalt();
            String password = PasswordHelper.encodePassword(pwd, salt);
            sysUser.setPassword(password);
            sysUser.setSalt(salt);
        }
        super.update(sysUser);
        this.assignRoles(sysUser.getId(), Lists.newArrayList(roleId));
    }
}
