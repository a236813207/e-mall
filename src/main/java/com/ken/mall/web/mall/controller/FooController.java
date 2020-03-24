package com.ken.mall.web.mall.controller;

import com.ken.mall.utils.MessageUtils;
import com.ken.mall.web.bind.response.ResBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ken
 * @date 2020/1/6
 * @description
 */
@RestController
@RequestMapping("/foo")
@Api(tags = "测试")
public class FooController {

    @GetMapping("/lang")
    @ApiOperation(value = "语言设置")
    public ResBody lang() {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        System.out.println(lang);
        System.out.println(MessageUtils.get("login.phone"));
        return ResBody.success();
    }

}
