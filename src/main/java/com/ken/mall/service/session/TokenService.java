package com.ken.mall.service.session;

/**
 * @author Ken
 * @date 2020/1/1
 * @description
 */
public interface TokenService {

    /**
     * 创建一个token关联上指定用户
     */
    void storeToken(String key, Token token);

    /**
     * token延长过期时间
     * @param key
     */
    void delayToken(String key);

    /**
     * 清除token
     */
    void deleteToken(String key);

    /**
     * 获取token
     * @param key
     * @return
     */
    Token getToken(String key);
}
