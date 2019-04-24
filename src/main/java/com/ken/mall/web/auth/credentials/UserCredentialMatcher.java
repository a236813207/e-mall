package com.ken.mall.web.auth.credentials;

import com.ken.mall.utils.password.PasswordHelper;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.util.ByteSource;

/**
 * @author Ken
 * @date 2019/4/24
 * @description
 */
public class UserCredentialMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utk = (UsernamePasswordToken) token;
        SimpleAuthenticationInfo authInfo = (SimpleAuthenticationInfo) info;
        String password = new String(utk.getPassword());
        String secret = authInfo.getCredentials() + "";
        ByteSource credentialsSalt = authInfo.getCredentialsSalt();
        String salt = new String(credentialsSalt.getBytes());
        return PasswordHelper.verifyPassword(password, secret, salt);
    }
}
