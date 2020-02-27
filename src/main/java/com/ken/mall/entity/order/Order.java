package com.ken.mall.entity.order;

import com.ken.mall.entity.AbstractEntity;
import com.ken.mall.entity.LogicDeleteable;
import com.ken.mall.entity.order.converter.OrderStatusEnumConverter;
import com.ken.mall.entity.order.converter.PaymentStatusEnumConverter;
import com.ken.mall.entity.order.enums.OrderStatusEnum;
import com.ken.mall.entity.order.enums.PaymentStatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Ken
 * @date 2018/11/02
 * @description
 */
@Entity
@Table(name = "e_order")
@Getter
@Setter
public class Order extends AbstractEntity<String> implements LogicDeleteable {

    @Id
    @Column(nullable = false, length = 32, insertable = false, updatable = false)
    private String id;

    @Column(name = "amount", columnDefinition="decimal(10,2) not null comment '订单金额'")
    private BigDecimal amount;

    @Column(name = "real_amount", columnDefinition="decimal(10,2) not null comment '订单实付金额'")
    private BigDecimal realAmount;

    @Column(name = "expire_time", columnDefinition="datetime default null comment '支付过期时间'")
    private Date expireTime;

    @Column(name = "payment_status", columnDefinition="tinyint(2) not null comment '支付状态'")
    @Convert(converter = PaymentStatusEnumConverter.class)
    private PaymentStatusEnum paymentStatus;

    @Column(name = "paid_time", columnDefinition="datetime default null comment '支付时间'")
    private Date paidTime;

    @Column(name = "trade_no", columnDefinition="varchar(128) not null comment '交易流水号'")
    private String tradeNo;

    @Column(name = "status", columnDefinition="tinyint(2) not null comment '订单状态'")
    @Convert(converter = OrderStatusEnumConverter.class)
    private OrderStatusEnum status;

    @Column(name = "goods_id", columnDefinition="bigint(20) not null comment '商品id'")
    private Long goodsId;

    @Column(name = "goods_name", columnDefinition="varchar(256) not null comment '商品名称'")
    private String goodsName;

    @Column(name = "member_id", columnDefinition="bigint(20) not null comment '会员id'")
    private Long memberId;

    @Column(name = "member_name", columnDefinition="varchar(32) not null comment '会员名称'")
    private String memberName;

    @Column(name = "consignee", columnDefinition="varchar(32) not null comment '收件人'")
    private String consignee;

    @Column(name = "phone", columnDefinition="varchar(32) not null comment '电话'")
    private String phone;

    @Column(name = "province", columnDefinition="varchar(32) not null comment '省'")
    private String province;

    @Column(name = "city", columnDefinition="varchar(32) not null comment '市'")
    private String city;

    @Column(name = "district", columnDefinition="varchar(32) not null comment '区'")
    private String district;

    @Column(name = "address", columnDefinition="varchar(255) not null comment '详细地址'")
    private String address;

    @Column(name = "member_coupon_id", columnDefinition="bigint(20) default null comment '会员优惠券id'")
    private Long memberCouponId;

    @Column(name = "deduct_coupon_money", columnDefinition="decimal(10,2) default null comment '优惠券抵扣金额'")
    private BigDecimal deductCouponMoney;

    @Column(name = "express_no", columnDefinition="varchar(32) default null comment '快递单号'")
    private String expressNo;

    @Column(name = "freight", columnDefinition="decimal(10,2) not null comment '运费'")
    private BigDecimal freight;

    @Column(name = "finish_time", columnDefinition="datetime default null comment '完成时间'")
    private Date finishTime;

    @Column(name = "memo", columnDefinition="varchar(255) default null comment '买家备注'")
    private String memo;

    private Boolean deleted = Boolean.FALSE;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Boolean getDeleted() {
        return this.deleted;
    }

    @Override
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public void markDeleted() {
        setDeleted(Boolean.TRUE);
    }
}
