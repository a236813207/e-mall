package com.ken.mall.web.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    @ApiOperation(value = "登录")
    public ModelAndView login() {
        logger.info("login=====");
        ModelAndView model = new ModelAndView();
        model.addObject("test","123");
        model.setViewName("log");
        return model;
    }

}
