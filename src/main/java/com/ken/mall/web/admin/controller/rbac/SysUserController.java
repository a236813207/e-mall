package com.ken.mall.web.admin.controller.rbac;

import com.ken.mall.entity.rbac.SysRole;
import com.ken.mall.entity.rbac.SysUser;
import com.ken.mall.pojo.search.Filter;
import com.ken.mall.pojo.search.PageBo;
import com.ken.mall.pojo.search.PageRequestBo;
import com.ken.mall.service.rbac.SysUserService;
import com.ken.mall.web.admin.controller.rbac.resp.SysUserResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Ken
 * @date 2019/7/28
 * @description
 */
@RequestMapping("/admin")
@Controller
@Api(value = "系统用户管理")
public class SysUserController {

    private SysUserService sysUserService;

    @GetMapping("/sysuser")
    @ApiOperation(value = "系统用户管理页面")
    public String index() {
        return "/admin/rbac/sysuser";
    }


    @PostMapping("/sysuser")
    @ApiOperation(value = "获取用户列表")
    @ResponseBody
    public PageBo list(@RequestParam(required = false) String userName,
                        @RequestParam(required = false) Integer roleId,
                        @ApiIgnore PageRequestBo page) {
        PageBo<SysUser> result = this.sysUserService.findPage(Filter.LikeValue.both(userName), roleId, page);

        List<SysUserResp> resps = result.getData().stream()
                .map(item -> {
                    SysUserResp resp = this.findAccountRoles(item);
                    return resp;
                }).collect(Collectors.toList());
        return new PageBo<>(resps, result.getRecordsTotal(), page);
    }


    private SysUserResp findAccountRoles(SysUser manager) {
        List<SysRole> roles = this.sysUserService.findRoles(manager.getUserName());
        SysUserResp resp = new SysUserResp();
        BeanUtils.copyProperties(manager, resp);
        roles.forEach(item -> {
            Map<String, Object> map = new HashMap<>();
            map.put("roleId", item.getId());
            map.put("role", item.getName());
            map.put("memo", item.getMemo());
            resp.getRoles().add(map);
        });
        return resp;
    }

    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }
}
