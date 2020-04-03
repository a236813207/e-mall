package com.ken.mall.web.mall.controller;

import com.ken.mall.entity.dic.Ad;
import com.ken.mall.entity.dic.enums.AdPositionEnum;
import com.ken.mall.service.dic.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Ken
 * @date 2020/3/21
 * @description
 */
@RequestMapping("/mall")
@Controller
public class MallIndexController {

    @Autowired
    private AdService adService;

    @GetMapping("/index")
    public String index(Model model) {
        List<Ad> ads = this.adService.findByAdPositionId(AdPositionEnum.INDEX_TOP.getValue());
        model.addAttribute("ads", ads);
        return "/mall/index";
    }

}
