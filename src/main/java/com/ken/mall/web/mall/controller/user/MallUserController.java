package com.ken.mall.web.mall.controller.user;

import com.ken.mall.constant.MallConstants;
import com.ken.mall.entity.member.Member;
import com.ken.mall.pojo.member.MemberVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ken
 * @date 2020/3/29
 * @description
 */
@RequestMapping("/mall")
@Controller
public class MallUserController {

    @GetMapping("/user")
    public String user(HttpServletRequest request, Model model) {
        Member member = (Member) request.getSession().getAttribute(MallConstants.SESSION_MEMBER);
        if (member != null) {
            MemberVo vo = new MemberVo();
            BeanUtils.copyProperties(member, vo);
            model.addAttribute("member", vo);
        }
        return "/mall/user/user";
    }

    @GetMapping("/user/setting")
    public String userSetting() {
        return "/mall/user/setting";
    }

    @GetMapping("/user/sesame")
    public String userSesame() {
        return "/mall/user/sesame";
    }

    @GetMapping("/user/participate")
    public String userParticipate() {

        return "/mall/user/participate";
    }
}
