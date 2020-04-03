package com.ken.mall.web.mall.interceptor;

import com.ken.mall.service.session.TokenService;
import com.ken.mall.web.mall.auth.TokenAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Ken
 * @date 2020/1/4
 * @description
 */
public class TokenAuthInterceptor extends HandlerInterceptorAdapter {

    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getRequestURI().startsWith("/mall")) {
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
            /*String tokenStr = request.getHeader("x-access-token");
            if (StringUtils.isEmpty(tokenStr)) {
                tokenStr = request.getParameter("access_token");
            }
            if (StringUtils.isEmpty(tokenStr)) {
                throw new BizException(BizCodeFace.createBizCode(ErrorCode.PERMISSION_DENIED));
            }
            Map<String, Claim> claims = JWTHelper.unSign(tokenStr);
            if (claims == null && !claims.containsKey("userId")) {
                throw new BizException(BizCodeFace.createBizCode(ErrorCode.PERMISSION_DENIED));
            }
            String key = claims.get("userId").asLong() + "";
            Token token = tokenService.getToken(key);
            if (token == null) {
                throw new BizException(BizCodeFace.createBizCode(ErrorCode.PERMISSION_DENIED));
            }
            request.setAttribute(MallConstants.CURRENT_USER, token);
            this.tokenService.delayToken(key);*/




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
