package com.ken.mall.entity.activity;

import com.ken.mall.entity.BaseEntity;
import com.ken.mall.entity.activity.converter.RushPurchaseStatusEnumConverter;
import com.ken.mall.entity.activity.enums.RushPurchaseStatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ken
 * @date 2018/11/02
 * @description 抢购生成日志
 */
@Entity
@Table(name = "rush_purchase_log")
@Getter
@Setter
public class RushPurchaseLog extends BaseEntity {

    @Column(name = "base_id", columnDefinition="bigint(20) not null comment '基础抢购活动id'")
    private Long baseId;

    @Column(name = "goods_id", columnDefinition="bigint(20) not null comment '商品id'")
    private Long goodsId;

    @Column(name = "activity_sn", columnDefinition="varchar(32) not null comment '活动编码'")
    private String activitySn;

    @Column(name = "start_time", columnDefinition="datetime default null comment '开始时间'")
    private String startTime;

    @Column(name = "end_time", columnDefinition="datetime default null comment '结束时间'")
    private String endTime;

    @Column(name = "sesame", columnDefinition="int(4) default null comment '消耗芝麻数'")
    private Integer sesame;

    @Column(name = "limits", columnDefinition="int(2) default null comment '限制次数'")
    private Integer limits;

    @Column(name = "required_participants", columnDefinition="int(4) not null comment '需参与总人数'")
    private Integer requiredParticipants;

    @Column(name = "status", columnDefinition="tinyint(2) not null comment '活动状态'")
    @Convert(converter = RushPurchaseStatusEnumConverter.class)
    private RushPurchaseStatusEnum status;

    @Column(name = "participants", columnDefinition="int(4) not null comment '已参与人数'")
    private Integer participants;
}
