package com.ken.mall.web.mall.controller.shop;

import com.ken.mall.pojo.search.PageBo;
import com.ken.mall.pojo.search.PageRequestBo;
import com.ken.mall.pojo.shop.GoodsDetailVo;
import com.ken.mall.pojo.shop.GoodsListVo;
import com.ken.mall.service.shop.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ken
 * @date 2020/3/29
 * @description
 */
@RequestMapping("/mall")
@Controller
public class MallShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/shop")
    public String shop(PageRequestBo page, Model model) {
        PageBo<GoodsListVo> goods = this.shopService.findGoodsPage(page);
        model.addAttribute("goods", goods);
        return "/mall/shop/shop";
    }

    @GetMapping("/shop/{id}")
    public String detail(@PathVariable Long id, Model model) {
        GoodsDetailVo detail = this.shopService.detail(id);
        model.addAttribute("detail", detail);
        return "/mall/shop/detail";
    }




}
