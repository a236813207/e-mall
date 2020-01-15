package com.ken.mall.entity.member;

import com.ken.mall.entity.BaseEntity;
import com.ken.mall.entity.member.converter.GenderEnumConverter;
import com.ken.mall.entity.member.converter.MemberStatusEnumConverter;
import com.ken.mall.entity.member.enums.GenderEnum;
import com.ken.mall.entity.member.enums.MemberStatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ken
 * @date 2019/7/28
 * @description
 */
@Entity
@Table(name = "member")
@Getter
@Setter
public class Member extends BaseEntity {

    @Column(name = "account_name", columnDefinition="varchar(32) not null comment '会员名称'")
    private String accountName;

    @Column(name = "password", columnDefinition="varchar(128) not null comment '密码'")
    private String password;

    @Column(name = "salt", columnDefinition="varchar(128) not null comment '密码盐值'")
    private String salt;

    @Column(name = "mobile", columnDefinition="varchar(16) default null comment '手机号'")
    private String mobile;

    @Column(name = "avatar", columnDefinition="varchar(255) default null comment '头像'")
    private String avatar;

    @Column(name = "nick_name", columnDefinition="varchar(128) default null comment '昵称'")
    private String nickName;

    @Column(name = "gender", columnDefinition="tinyint(2) default null comment '性别'")
    @Convert(converter = GenderEnumConverter.class)
    private GenderEnum gender;

    @Column(name = "address", columnDefinition="varchar(512) default null comment '地址'")
    private String address;

    /**
    @Column(name = "wx_open_id", columnDefinition="varchar(128) default null comment '微信openid'")
    private String wxOpenId;

    @Column(name = "wx_union_id", columnDefinition="varchar(128) default null comment '微信unionid'")
    private String wxUnionId;
    */

    @Column(name = "status", columnDefinition="tinyint(2) not null comment '状态'")
    @Convert(converter = MemberStatusEnumConverter.class)
    private MemberStatusEnum status;

}
