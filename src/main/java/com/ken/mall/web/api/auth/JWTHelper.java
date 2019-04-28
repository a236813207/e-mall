package com.ken.mall.web.api.auth;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ken.mall.pojo.exception.BizException;
import com.ken.mall.pojo.exception.codes.BizCodeFace;
import com.ken.mall.pojo.exception.codes.ErrorCode;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * com.xfbetter.web.wx.mp.auth
 * author Daniel
 * 2018/1/6.
 */
public class JWTHelper {

    private static String secret = "XX#$%()(#*!()!KL<><sdys9seBs dsddsgdsddddd>?N<:{LWPW";

    public static String sign(long userId, String tokenType, long expirySeconds){
        LocalDateTime now = LocalDateTime.now();
        // 过期时间7个小时
        Date expiresDate = Date.from(now.plusSeconds(expirySeconds).atZone(ZoneId.systemDefault()).toInstant());

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        // param backups {iss:Service, aud:APP}
        try {
            String token = JWT.create().withHeader(map) // header
                    .withClaim("iss", "Service") // payload
                    .withClaim("aud", tokenType)
                    .withClaim("userId", userId)
                    .withIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant())) // sign time
                    .withExpiresAt(expiresDate) // expire time
                    .sign(Algorithm.HMAC256(secret)); // signature
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
}
