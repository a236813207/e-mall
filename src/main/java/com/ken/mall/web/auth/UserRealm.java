package com.ken.mall.web.auth;

import com.ken.mall.entity.sys.SysUser;
import com.ken.mall.service.SysUserService;
import com.ken.mall.utils.password.PasswordHelper;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRealm implements Realm {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public String getName() {
        return "simple realm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return true;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials()); //得到密码

        SysUser user = sysUserService.findByUsername(username);

        if (user == null) {
            throw new UnknownAccountException("账号不存在");//没找到帐号
        }
        if (!PasswordHelper.verifyPassword(password, user.getPassword(), user.getSalt())) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        return new SimpleAuthenticationInfo(
                user.getUserName(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getSalt()),
                getName()  //realm name
        );
    }
}
