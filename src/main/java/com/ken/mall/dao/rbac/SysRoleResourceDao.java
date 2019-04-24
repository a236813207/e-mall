package com.ken.mall.dao.rbac;

import com.ken.mall.dao.GenericRepository;
import com.ken.mall.entity.rbac.SysRoleResource;
import com.ken.mall.entity.rbac.SysRoleResourcePK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
public interface SysRoleResourceDao extends GenericRepository<SysRoleResource,SysRoleResourcePK> {

    /**
     * 删除角色的权限
     * @param roleId   角色Id
     */
    @Query("delete from SysRoleResource where roleId = ?1")
    @Modifying
    void deleteByRoleId(Long roleId);

}
