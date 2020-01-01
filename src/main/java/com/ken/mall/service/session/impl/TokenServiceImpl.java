package com.ken.mall.service.session.impl;

import com.ken.mall.service.session.Token;
import com.ken.mall.service.session.TokenService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.SerializationException;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Ken
 * @date 2020/1/1
 * @description
 */
public class TokenServiceImpl implements TokenService {

    private static final String PREFIXX = "USER_TOKEN_";

    private RedisTemplate redisTemplate;

    //token过期时间
    @Value("${token.hours}")
    private int hours = 1;

    @Resource
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void storeToken(String key, Token token) {
        this.redisTemplate.opsForValue().set(PREFIXX + key, token, hours, TimeUnit.HOURS);
    }

    @Override
    public void delayToken(String key) {
        this.redisTemplate.expire(PREFIXX + key, hours, TimeUnit.HOURS);
    }

    @Override
    public void deleteToken(String key) {
        this.redisTemplate.delete(PREFIXX + key);
    }

    @Override
    public Token getToken(String key) {
        try {
            Object object = this.redisTemplate.opsForValue().get(PREFIXX + key);
            if (object instanceof Token) {
                return (Token) object;
            }
            return null;
        } catch (SerializationException | JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
