package com.ken.mall.service;

import com.ken.mall.entity.sys.SysUser;
import com.wwbetter.service.pojo.exception.BizException;
import com.wwbetter.service.pojo.search.PageBo;
import com.wwbetter.service.pojo.search.PageRequestBo;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
public interface SysUserService extends BaseService<SysUser, Integer> {

    String PASSWORD = "123456";

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 管理员
     * @throws BizException 业务异常
     */
    SysUser findByUsername(String username) throws BizException;

    /**
     * 查询管理员列表
     *
     * @return 带分页信息的管理员列表
     */
    PageBo<SysUser> findPage(String nameKey, PageRequestBo page);


    /**
     * 创建新管理员
     *
     * @param sysUser 内容
     * @throws BizException
     */
    void createNew(SysUser sysUser) throws BizException;

    /**
     * 重置密码
     *
     * @param id 用户ID
     * @throws BizException
     */
    void resetPassword(Integer id) throws BizException;
}
