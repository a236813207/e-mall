package com.ken.mall.service.member;

import com.ken.mall.entity.member.Member;
import com.ken.mall.service.base.BaseService;

/**
 * @author Ken
 * @date 2020/3/21
 * @description
 */
public interface MemberService extends BaseService<Member, Long> {

    /**
     * 根据电话查找
     * @param phone
     * @return
     */
    Member findByPhone(String phone);

    /**
     * 手机号登录
     * @param phone
     * @return
     */
    Member loginByPhone(String phone);

}
