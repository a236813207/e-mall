package com.ken.mall.entity.member;

import com.ken.mall.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ken
 * @date 2019/7/28
 * @description 收货地址
 */
@Entity
@Table(name = "member_address")
@Getter
@Setter
public class MemberAddress extends BaseEntity {

    @Column(name = "member_id", columnDefinition="bigint(20) not null comment '会员id'")
    private Long memberId;

    @Column(name = "consignee", columnDefinition="varchar(32) not null comment '收件人'")
    private String consignee;

    @Column(name = "province", columnDefinition="varchar(32) not null comment '省'")
    private String province;

    @Column(name = "city", columnDefinition="varchar(32) not null comment '市'")
    private String city;

    @Column(name = "district", columnDefinition="varchar(32) not null comment '区县'")
    private String district;

    @Column(name = "address", columnDefinition="varchar(255) not null comment '详细地址'")
    private String address;

    @Column(name = "phone", columnDefinition="varchar(32) not null comment '电话'")
    private String phone;

    @Column(name = "is_default", columnDefinition="tinyint(2) not null comment '是否默认'")
    private Boolean isDefault;

}
