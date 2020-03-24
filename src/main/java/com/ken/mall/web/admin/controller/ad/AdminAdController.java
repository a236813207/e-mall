package com.ken.mall.web.admin.controller.ad;

import com.google.common.collect.Lists;
import com.ken.mall.entity.dic.Ad;
import com.ken.mall.pojo.search.OrderBo;
import com.ken.mall.pojo.search.PageBo;
import com.ken.mall.pojo.search.PageRequestBo;
import com.ken.mall.service.dic.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author Ken
 * @date 2020/3/23
 * @description
 */
@RequestMapping("/admin")
@Controller
public class AdminAdController {

    @Autowired
    private AdService adService;

    @GetMapping("/ad")
    public String index() {
        return "/admin/ad/ad";
    }

    @PostMapping("/ad")
    @ResponseBody
    public PageBo list(@ApiIgnore PageRequestBo page) {
        PageBo<Ad> pageBo = this.adService.findPage(page, Lists.newArrayList(), Lists.newArrayList(OrderBo.asc("orders")));
        return pageBo;
    }
}
