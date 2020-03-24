package com.ken.mall.entity.activity;

import com.ken.mall.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ken
 * @date 2019/7/28
 * @description 抢购活动基础表
 */
@Entity
@Table(name = "rush_purchase_base")
@Getter
@Setter
public class RushPurchaseBase extends BaseEntity {

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

    @Column(name = "is_loop", columnDefinition="tinyint(1) unsigned default '0' comment '是否循环'")
    private boolean isLoop;

    @Column(name = "intervals", columnDefinition="int(4) unsigned default '0' comment '循环生产间隔时间(分)'")
    private Integer intervals;

}
