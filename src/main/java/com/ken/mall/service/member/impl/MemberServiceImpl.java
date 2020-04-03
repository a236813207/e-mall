package com.ken.mall.service.member.impl;

import com.ken.mall.dao.member.MemberDao;
import com.ken.mall.entity.member.Member;
import com.ken.mall.entity.member.enums.MemberStatusEnum;
import com.ken.mall.service.base.impl.BaseServiceImpl;
import com.ken.mall.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ken
 * @date 2020/3/21
 * @description
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<Member, Long> implements MemberService  {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findByPhone(String phone) {
        return this.memberDao.findTopByMobile(phone);
    }

    @Override
    public Member loginByPhone(String phone) {
        Member member = this.memberDao.findTopByMobile(phone);
        if(member == null) {
            member = new Member();
            member.setMobile(phone);
            member.setAccountName(phone);
            member.setStatus(MemberStatusEnum.NORMAL);
            return this.save(member);
        }
        return member;
    }
}
