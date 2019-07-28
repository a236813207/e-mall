package com.ken.mall.entity.order;

import com.ken.mall.entity.BaseEntity;
import com.ken.mall.entity.order.enums.OrderLogStatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ken
 * @date 2018/11/02
 * @description
 */
@Entity
@Table(name = "e_order_log")
@Getter
@Setter
public class OrderLog extends BaseEntity {

    @Column(name = "order_id", columnDefinition="varchar(32) not null comment '订单ID'")
    private Long orderId;

    @Column(name = "status", columnDefinition="tinyint(2) not null comment '订单日志状态'")
    private OrderLogStatusEnum status;

    @Column(name = "operator", columnDefinition="varchar(32) not null comment '操作人'")
    private String operator;

}
