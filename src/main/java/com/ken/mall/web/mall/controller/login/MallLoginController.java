package com.ken.mall.web.mall.controller.login;

import com.ken.mall.constant.MallConstants;
import com.ken.mall.entity.member.Member;
import com.ken.mall.exception.codes.ErrorCode;
import com.ken.mall.service.member.MemberService;
import com.ken.mall.utils.CaptchaRenderUtils;
import com.ken.mall.web.bind.response.ResBody;
import com.ken.mall.web.mall.auth.TokenAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * @author Ken
 * @date 2020/1/4
 * @description
 */
@Controller
@RequestMapping("/mall")
@Api(tags = "登录")
public class MallLoginController {

    private final static Logger logger = LoggerFactory.getLogger(MallLoginController.class);

    @Autowired
    private MemberService memberService;

    @GetMapping("/login")
    public String login(@ApiIgnore HttpServletRequest request) {
        String sessionPhone = (String) request.getSession().getAttribute(MallConstants.SESSION_PHONE);
        if (!StringUtils.isEmpty(sessionPhone)) {
            return "redirect:/mall/index";
        }
        return "/mall/login";
    }

    @GetMapping("/validcode")
    public void validcode(HttpServletRequest request, HttpServletResponse response) {
        CaptchaRenderUtils captchaRender = new CaptchaRenderUtils();
        String md5RandonCode = captchaRender.getMd5RandonCode();
        request.getSession().setAttribute(MallConstants.SESSION_VALIDCODE, md5RandonCode);
        captchaRender.render(response);
    }

    @PostMapping("/login")
    @ApiOperation(value = "短信验证码登录")
    @ResponseBody
    public ResBody smsLogin(String phone, String smsCode, String validCode, @ApiIgnore HttpServletRequest request, @ApiIgnore HttpServletResponse response) {
        // 检查手机号、验证码等
        ResBody resBody = checkData(phone, validCode, request);
        if (ErrorCode.OK.getCode() != resBody.getCode()) {
            return resBody;
        }
        if (StringUtils.isEmpty(smsCode)) {
            return ResBody.failure("请输入短信验证码");
        }
        // 判断手机验证码
        String storeSmsCode = (String) request.getSession().getAttribute(MallConstants.SESSION_SMSCODE);
        if (StringUtils.isEmpty(smsCode) || !smsCode.equals(storeSmsCode)) {
            return ResBody.failure("手机验证码错误");
        }

        request.getSession().removeAttribute(MallConstants.SESSION_SMSCODE);
        Member member = this.memberService.loginByPhone(phone);
        request.getSession().setAttribute(MallConstants.SESSION_MEMBER, member);

        return ResBody.success();
    }

    @PostMapping("/sendSms")
    @ApiOperation(value = "发送短信验证码")
    @ResponseBody
    public ResBody sendSms(@NotNull String phone, String validCode, HttpServletRequest request) {
        ResBody resBody = checkData(phone, validCode, request);
        if (ErrorCode.OK.getCode() != resBody.getCode()) {
            return resBody;
        }
        //String smsCode = NumberUtils.getCode(4);
        String smsCode = "1234";
        request.getSession().setAttribute("smsCode", smsCode);
        //todo 发送手机短信
        return ResBody.success().data(smsCode).message("发送成功");
    }

    private ResBody checkData(String phone, String validCode, HttpServletRequest request) {
        if (StringUtils.isEmpty(phone)) {
            return ResBody.failure("请输入手机号码");
        }
        if (StringUtils.isEmpty(validCode)) {
            return ResBody.failure("请输入图形验证码");
        }
        if(!CaptchaRenderUtils.validate((String)request.getSession().getAttribute(MallConstants.SESSION_VALIDCODE), validCode)) {
            return ResBody.failure("图形验证码错误");
        }
        return ResBody.success();
    }

    @GetMapping("/foo")
    @ApiOperation(value = "foo")
    @TokenAuth
    public ResBody foo() {
        return ResBody.success().data("123456");
    }

}
