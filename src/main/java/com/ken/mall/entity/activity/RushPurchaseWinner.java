package com.ken.mall.entity.activity;

import com.ken.mall.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ken
 * @date 2020/2/9
 * @description 中奖人信息
 */
@Entity
@Table(name = "rush_purchase_winner")
@Getter
@Setter
public class RushPurchaseWinner extends BaseEntity {

    @Column(name = "member_id", columnDefinition="bigint(20) not null comment '会员ID'")
    private Long memberId;

    @Column(name = "rush_purchase_id", columnDefinition="bigint(20) not null comment '抢购活动ID'")
    private Long rushPurchaseId;

    @Column(name = "consignee", columnDefinition="varchar(128) not null comment '收件人'")
    private String consignee;

    @Column(name = "province", columnDefinition="varchar(128) not null comment '省'")
    private String province;

    @Column(name = "city", columnDefinition="varchar(128) not null comment '市'")
    private String city;

    @Column(name = "district", columnDefinition="varchar(128) not null comment '区县'")
    private String district;

    @Column(name = "address", columnDefinition="varchar(256) not null comment '详细地址'")
    private String address;

    @Column(name = "phone", columnDefinition="varchar(32) not null comment '电话'")
    private String phone;

}
