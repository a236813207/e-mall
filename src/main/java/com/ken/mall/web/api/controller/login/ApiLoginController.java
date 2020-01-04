package com.ken.mall.web.api.controller.login;

import com.ken.mall.constant.RegexPatterns;
import com.ken.mall.service.session.Token;
import com.ken.mall.service.session.TokenService;
import com.ken.mall.web.api.auth.JWTHelper;
import com.ken.mall.web.api.auth.TokenAuth;
import com.ken.mall.web.bind.annotation.CurrentUser;
import com.ken.mall.web.bind.response.ResBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Ken
 * @date 2020/1/4
 * @description
 */
@RestController
@RequestMapping("/api")
@Api(tags = "登录")
public class ApiLoginController {

    private final static Logger logger = LoggerFactory.getLogger(ApiLoginController.class);

    private TokenService tokenService;

    @GetMapping("/login")
    @ApiOperation(value = "短信验证码登录")
    public ResBody smsLogin(@RequestParam String username, @RequestParam String smsCode, @ApiIgnore HttpServletResponse response) {
        Assert.isTrue(username.matches(RegexPatterns.MOBILE_PHONE_NUMBER_PATTERN), "请输入正确的手机号格式");
        Token tk = new Token();
        tk.setUserId(10L);
        String sign = JWTHelper.sign(10, "h5-app");
        tk.setSign(sign);
        //将openid为key为key进行存储
        tokenService.storeToken("10", tk);
        response.addHeader("access-token", sign);
        //throw new BizException(BizCodeFace.createBizCode(ErrorCode.AUTH_FAIL).message("验证码错误"));
        return ResBody.success();
    }

    @GetMapping("/foo")
    @ApiOperation(value = "foo")
    @TokenAuth
    public ResBody foo(@CurrentUser Token token) {
        System.out.println(token.getUserId());
        System.out.println(token.getSign());
        return ResBody.success();
    }


    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }
}
