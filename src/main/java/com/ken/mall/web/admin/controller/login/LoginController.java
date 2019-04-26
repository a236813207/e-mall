package com.ken.mall.web.admin.controller.login;

import com.ken.mall.constant.AdminConst;
import com.ken.mall.entity.rbac.SysUser;
import com.ken.mall.pojo.exception.BizException;
import com.ken.mall.pojo.exception.codes.BizCodeFace;
import com.ken.mall.pojo.exception.codes.ErrorCode;
import com.ken.mall.web.admin.controller.login.resp.ManagerInfoResp;
import com.ken.mall.web.bind.response.ResBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ken
 * @date 2019/4/24
 * @description
 */
@Controller
@RequestMapping("/admin")
@Api(tags = "登录")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/login")
    @ApiOperation(value = "登录页面")
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.isAuthenticated()) {
            //SysUser manager = (SysUser) subject.getSession().getAttribute(AdminConst.MEMBER_SESSION_ATTR_KEY);
            //ManagerInfoResp rs = new ManagerInfoResp(manager.getUserName(), manager.getId());
            model.setViewName("redirect:/admin/main");
        }else {
            model.setViewName("login");
        }
        return model;
    }

    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "登录", response = ResBody.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户名", name = "username", paramType = "form", defaultValue = "admin"),
            @ApiImplicitParam(value = "密码", name = "password", paramType = "form", defaultValue = "123456")
    })
    public ResBody login(@ApiIgnore HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.isAuthenticated()) {
            SysUser manager = (SysUser) subject.getSession().getAttribute(AdminConst.MEMBER_SESSION_ATTR_KEY);
            ManagerInfoResp rs = new ManagerInfoResp(manager.getUserName(), manager.getId());
            return ResBody.success(rs);
        }
        String shiroLoginFailureEx =
                (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);

        if (IncorrectCredentialsException.class.getName().equals(shiroLoginFailureEx)) {
            throw new BizException(BizCodeFace.createBizCode(ErrorCode.AUTH_FAIL));
        } else if (LockedAccountException.class.getName().equals(shiroLoginFailureEx)) {
            throw new BizException(BizCodeFace.createBizCode(ErrorCode.ACCOUNT_LOCKED));
        } else if (UnknownAccountException.class.getName().equals(shiroLoginFailureEx)) {
            throw new BizException(BizCodeFace.createBizCode(ErrorCode.ACCOUNT_NO_EXISTIS));
        }
        throw new BizException(BizCodeFace.createBizCode(ErrorCode.AUTH_FAIL));
    }

    @GetMapping("/main")
    @ApiOperation(value = "主页")
    public ModelAndView main() {
        ModelAndView model = new ModelAndView();
        model.setViewName("main");
        return model;
    }

    @GetMapping("/index")
    @ApiOperation(value = "首页")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }

    @GetMapping("/logout")
    @ApiOperation(value = "退出登录")
    public ModelAndView logout() {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        } catch (SessionException ise) {
            logger.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        }
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }
}
