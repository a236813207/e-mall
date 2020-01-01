package com.ken.mall.web.api.interceptor;

import com.auth0.jwt.interfaces.Claim;
import com.ken.mall.exception.BizException;
import com.ken.mall.exception.codes.BizCodeFace;
import com.ken.mall.exception.codes.ErrorCode;
import com.ken.mall.service.session.TokenService;
import com.ken.mall.web.api.auth.JWTHelper;
import com.ken.mall.web.api.auth.TokenAuth;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * @author Daniel
 * 2015/10/27
 */
public class TokenAuthInterceptor extends HandlerInterceptorAdapter {

    private TokenService tokenService;
    //private MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getRequestURI().startsWith("/api")) {
            return true;
        }
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        TokenAuth auth = method.getAnnotation(TokenAuth.class);
        if (auth == null) {
            auth = method.getDeclaringClass().getAnnotation(TokenAuth.class);
        }
        if (auth == null) {
            return true;
        }
        //获取token
        if (auth.require()) {
            String tokenStr = request.getHeader("x-access-token");
            if (StringUtils.isEmpty(tokenStr)) {
                tokenStr = request.getParameter("access_token");
            }
            if (StringUtils.isNotEmpty(tokenStr)) {
                Map<String, Claim> claims = JWTHelper.unSign(tokenStr);
                if (claims != null && claims.containsKey("userId")) {
                    long userId = claims.get("userId").asLong();
                    Date expiry = claims.get("exp").asDate();
                    if (expiry.before(new Date())) {
                        throw new BizException(BizCodeFace.createBizCode(ErrorCode.PERMISSION_EXPIRED));
                    }
                    if (userId > 0 ) {
                        // 查找用户信息并存入session中 TODO
                        //request.setAttribute(Constants.CURRENT_USER, model);
                    } else {
                        throw new BizException(BizCodeFace.createBizCode(ErrorCode.PERMISSION_DENIED));
                    }
                }
            }
        }
        return true;
    }

    @Autowired
    @Lazy
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /*
    @Autowired
    @Lazy
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }*/
}
