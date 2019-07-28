package com.ken.mall.web.admin.controller.sysuser.resp;

import com.ken.mall.entity.rbac.enums.SysUserStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ken
 * @date 2019/7/28
 * @description
 */
@Getter
@Setter
public class SysUserResp {

    private String userName;

    private SysUserStatusEnum status;

    private List<Map<String,Object>> roles = new ArrayList<>();
}
