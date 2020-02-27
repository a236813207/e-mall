package com.ken.mall.entity.activity;

import com.ken.mall.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ken
 * @date 2018/11/02
 * @description 抢购参与日志
 */
@Entity
@Table(name = "rush_purchase_participate_log")
@Getter
@Setter
public class RushPurchaseParticipateLog extends BaseEntity {

    @Column(name = "member_id", columnDefinition="bigint(20) not null comment '会员ID'")
    private Long memberId;

    @Column(name = "rush_purchase_id", columnDefinition="bigint(20) not null comment '抢购活动ID'")
    private Long rushPurchaseId;

    @Column(name = "code", columnDefinition="varchar(32) not null comment '编码'")
    private String code;

}
