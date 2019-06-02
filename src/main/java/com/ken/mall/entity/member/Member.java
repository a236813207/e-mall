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
import java.util.Date;

@Entity
@Table(name = "member")
@Getter
@Setter
public class Member extends BaseEntity {

    //账号名称
    @Column(length = 32, nullable = false, name = "account_name")
    private String accountName;

    //密码
    @Column(length = 128, nullable = false, name = "password")
    private String password;

    //密码的盐
    @Column(length = 128, nullable = false, name = "salt")
    private String salt;

    //注册时间
    @Column(nullable = false, name = "register_time")
    private Date registerTime;

    //手机号码
    @Column(length = 32, name = "mobile")
    private String mobile;

    //头像url
    @Column(length = 500, name = "avatar_url")
    private String avatarUrl;

    //昵称
    @Column(length = 128, name = "nick_name")
    private String nickName;

    //性别
    @Column(nullable = false, length = 1, name = "gender")
    @Convert(converter = GenderEnumConverter.class)
    private GenderEnum gender;

    //详细地址
    @Column(length = 1024, name = "address")
    private String address;

    //微信openid
    @Column(length = 128, name = "wx_open_id")
    private String wxOpenId;

    //微信unionid
    @Column(length = 128, name = "wx_union_id")
    private String wxUnionId;

    //使用状态
    @Column(nullable = false, length = 1, name = "status")
    @Convert(converter = MemberStatusEnumConverter.class)
    private MemberStatusEnum status;

}
