package com.ken.mall.dao.member;

import com.ken.mall.dao.GenericRepository;
import com.ken.mall.entity.member.Member;

/**
 * @author Ken
 * @date 2020/3/21
 * @description
 */
public interface MemberDao extends GenericRepository<Member, Long> {

    Member findTopByMobile(String mobile);
}
