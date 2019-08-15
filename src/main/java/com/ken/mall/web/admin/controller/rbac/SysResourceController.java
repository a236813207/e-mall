package com.ken.mall.web.admin.controller.rbac;

import com.ken.mall.service.rbac.SysResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ken
 * @date 2019/8/1
 * @description
 */
@Controller
@RequestMapping("/admin/resource")
@Api(value = "菜单管理")
public class SysResourceController {

    private SysResourceService sysResourceService;


    @GetMapping("")
    @ApiOperation(value = "菜单管理页面")
    public String index() {
        return "/rbac/sysresource";
    }



    @Autowired
    public void setSysResourceService(SysResourceService sysResourceService) {
        this.sysResourceService = sysResourceService;
    }
}
