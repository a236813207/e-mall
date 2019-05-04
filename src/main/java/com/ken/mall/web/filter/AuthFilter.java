package com.ken.mall.web.filter;

import com.ken.mall.entity.rbac.SysUser;
import com.ken.mall.pojo.exception.codes.ErrorCode;
import com.ken.mall.service.SysUserService;
import com.ken.mall.web.bind.response.ResBody;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
public class AuthFilter extends FormAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(FormAuthenticationFilter.class);

    private SysUserService sysUserService;
    private MappingJackson2HttpMessageConverter jsonConverter;

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public void setJsonConverter(MappingJackson2HttpMessageConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("登录GET请求。放行，由登录接口自动处理");
                }
                //
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("权限不足");
            }
            HttpServletRequest servletRequest = WebUtils.toHttp(request);
            //如果是ajax请求，返回未授权
            if (servletRequest.getHeader("X-Requested-With") != null && servletRequest.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
                HttpServletResponse servletResponse = WebUtils.toHttp(response);
                servletResponse.setCharacterEncoding("utf-8");
                servletResponse.setHeader("Content-type", "application/json;charset=UTF-8");
                String out = jsonConverter.getObjectMapper().writeValueAsString(ResBody.custom(ErrorCode.PERMISSION_EXPIRED.getCode(), ErrorCode.PERMISSION_EXPIRED.getMessage()));
                servletResponse.getWriter().write(out);
                //servletResponse.sendError(HttpStatus.UNAUTHORIZED.value());
            } else {
                redirectToLogin(request, response);
            }

            return false;
        }
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                String username = getUsername(request);
                Subject subject = getSubject(request, response);
                SysUser sysUser = (SysUser) subject.getPrincipal();
                if (sysUser != null && username != null && !sysUser.getUserName().equals(username)) {
                    subject.logout();
                }
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        return super.onLoginFailure(token, e, request, response);
    }
}
