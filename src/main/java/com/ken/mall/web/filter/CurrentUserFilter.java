package com.ken.mall.web.filter;

import com.ken.mall.constant.AdminConst;
import com.ken.mall.constant.Constants;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 每次请求设置currentUser
 * com.xfbetter.web.filter
 * author Daniel
 * 2017/12/19.
 */
public class CurrentUserFilter extends PathMatchingFilter {

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        request.setAttribute(Constants.CURRENT_USER, session.getAttribute(AdminConst.MEMBER_SESSION_ATTR_KEY));
        return true;
    }
}
