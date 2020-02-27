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
 * @description 抢购活动评价
 */
@Entity
@Table(name = "rush_purchase_comment")
@Getter
@Setter
public class RushPurchaseComment extends BaseEntity {

    @Column(name = "content", columnDefinition="varchar(1024) collate utf8mb4_unicode_ci default null comment '评价文字'")
    private String content;

    @Column(name = "rush_purchase_id", columnDefinition="bigint(20) not null comment '抢购活动id'")
    private Long rushPurchaseId;

    @Column(name = "member_id", columnDefinition="bigint(20) not null comment '会员id'")
    private Long memberId;


}
