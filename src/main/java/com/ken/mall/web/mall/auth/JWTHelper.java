package com.ken.mall.web.mall.auth;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ken.mall.exception.BizException;
import com.ken.mall.exception.codes.BizCodeFace;
import com.ken.mall.exception.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ken
 * @date 2019/4/28
 * @description
 */
@Component
public class JWTHelper {

    private static String secret = "XX#$%()(#*!()!KL<><sdys9seBs dsddsgdsddddd>?N<:{LWPW";

    @Value("${token.expiry}")
    private static long expiry = 7;

    public static String sign(long userId, String tokenType) {
        return sign(userId, tokenType, expiry);
    }

    public static String sign(long userId, String tokenType, long expiryHours){
        LocalDateTime now = LocalDateTime.now();
        // 过期时间
        Date expiresDate = Date.from(now.plusHours(expiryHours).atZone(ZoneId.systemDefault()).toInstant());

        // header Map
        Map<String, Object> map = new HashMap<>(2);
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        // param backups {iss:Service, aud:APP}
        /*
        iss: jwt签发者
        sub: jwt所面向的用户
        aud: 接收jwt的一方
        exp: jwt的过期时间，这个过期时间必须要大于签发时间
        nbf: 定义在什么时间之前，该jwt都是不可用的.
        iat: jwt的签发时间
        jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
        */
        try {
            String token = JWT.create().withHeader(map)
                    .withClaim("iss", "Service")
                    .withClaim("aud", tokenType)
                    .withClaim("userId", userId)
                    .withIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                    .withExpiresAt(expiresDate)
                    .sign(Algorithm.HMAC256(secret));
            return token;
        } catch (Exception e) {
            throw new BizException(BizCodeFace.createBizCode(ErrorCode.DATE_NULL));
        }
    }

    public static Map<String, Claim> unSign(String token){
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new BizException(BizCodeFace.createBizCode(ErrorCode.PARAM_ERROR).message("非法请求"));
        }
        return jwt.getClaims();
    }

    public static void main(String[] args){
        String token = sign(10, "h5_app", 600);
        System.out.println(token);
        Map<String, Claim> decodeToken = unSign(token);
        System.out.println(decodeToken);
        Claim aud = decodeToken.get("aud");
        System.out.println("aud:"+aud.asString());
        Claim iss = decodeToken.get("iss");
        System.out.println("iss:"+iss.asString());
        Claim exp = decodeToken.get("exp");
        System.out.println("exp:"+exp.asDate());
        Claim userId = decodeToken.get("userId");
        System.out.println("userId:"+userId.asLong());
        Claim iat = decodeToken.get("iat");
        System.out.println("iat:"+iat.asDate());
    }
}
