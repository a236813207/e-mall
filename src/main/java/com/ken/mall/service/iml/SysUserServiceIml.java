package com.ken.mall.service.iml;

import com.google.common.collect.Lists;
import com.ken.mall.dao.sys.SysUserDao;
import com.ken.mall.entity.sys.SysUser;
import com.ken.mall.service.SysUserService;
import com.ken.mall.utils.password.PasswordHelper;
import com.wwbetter.service.pojo.exception.BizException;
import com.wwbetter.service.pojo.exception.codes.BizCodeFace;
import com.wwbetter.service.pojo.exception.codes.ErrorCode;
import com.wwbetter.service.pojo.search.Filter;
import com.wwbetter.service.pojo.search.PageBo;
import com.wwbetter.service.pojo.search.PageRequestBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * com.anteng.quarantine.service.iml.rbac
 * author Daniel
 * 2018/2/8.
 */
@Service("sysUserServiceIml")
@Transactional
public class SysUserServiceIml extends BaseServiceIml<SysUser, Integer> implements SysUserService {


    @Override
    public SysUser findByUsername(String username) throws BizException {
        SysUserDao userDao = (SysUserDao) getRepository();
        return userDao.findByUserName(username);
    }


    @Override
    public PageBo<SysUser> findPage(String nameKey, PageRequestBo page) {
        return this.findPage(page, Lists.newArrayList(
                Filter.like("username", Filter.LikeValue.both(nameKey))
        ), null);
    }

    @Override
    public void createNew(SysUser sysUser) throws BizException {
        String salt = PasswordHelper.generateSalt();
        String password = PasswordHelper.encodePassword(sysUser.getPassword(), salt);
        sysUser.setPassword(password);
        sysUser.setSalt(salt);
        this.save(sysUser);
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
    @Transactional(propagation = Propagation.REQUIRED)
    public SysUser update(SysUser entity) {
        return super.update(entity);
    }
}
