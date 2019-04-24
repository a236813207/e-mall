package com.ken.mall.dao.rbac;

import com.ken.mall.dao.GenericRepository;
import com.ken.mall.entity.rbac.SysResource;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
public interface SysResourceDao extends GenericRepository<SysResource,Long> {
    void deleteByParentIdsStartingWith(String parentIds);
}
