package com.ken.mall.web.filter;

import com.ken.mall.constant.AdminConst;
import com.ken.mall.entity.sys.SysUser;
import com.ken.mall.service.SysUserService;
import com.ken.mall.web.bind.response.ResBody;
import com.wwbetter.service.pojo.exception.codes.ErrorCode;
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
import javax.servlet.http.HttpServletResponse;

/**
 * com.xfbetter.web.filter
 * author Daniel
 * 2017/12/19.
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
            HttpServletResponse servletResponse = WebUtils.toHttp(response);
            servletResponse.setCharacterEncoding("utf-8");
            servletResponse.setHeader("Content-type", "application/json;charset=UTF-8");
            String out = jsonConverter.getObjectMapper().writeValueAsString(ResBody.custom(ErrorCode.PERMISSION_EXPIRED.getCode(), ErrorCode.PERMISSION_EXPIRED.getMessage()));
            servletResponse.getWriter().write(out);
            return false;
        }
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                String username = getUsername(request);
                Subject subject = getSubject(request, response);
                String logined = (String) subject.getPrincipal();
                if (logined != null && username != null && !logined.equals(username)) {
                    subject.logout();
                }
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        SysUser manager = sysUserService.findByUsername(token.getPrincipal() + "");
        //HttpServletRequest servletRequest = WebUtils.toHttp(request);
        subject.getSession().setAttribute(AdminConst.MEMBER_SESSION_ATTR_KEY, manager);
        return true;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        return super.onLoginFailure(token, e, request, response);
    }
}
