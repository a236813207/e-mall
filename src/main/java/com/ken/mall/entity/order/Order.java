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

@Entity
@Table(name = "order")
@Getter
@Setter
public class Order extends AbstractEntity<String> implements LogicDeleteable {

    @Id
    @Column(nullable = false, length = 32, insertable = false, updatable = false)
    private String id;

    @Column(name = "amount", nullable = false, precision=10, scale=2)
    private BigDecimal amount;

    @Column(name = "realAmount", nullable = false, precision=10, scale=2)
    private BigDecimal realAmount;

    @Column(name = "expire_time")
    private Date expireTime;

    @Column(name = "payment_status", length = 1, nullable = false)
    @Convert(converter = PaymentStatusEnumConverter.class)
    private PaymentStatusEnum paymentStatus;

    @Column(name = "paid_time")
    private Date paidTime;

    @Column(name = "trade_no", length = 128)
    private String tradeNo;

    @Column(name = "status", length = 1, nullable = false)
    @Convert(converter = OrderStatusEnumConverter.class)
    private OrderStatusEnum status;

    @Column(name = "member_id", length = 20, nullable = false)
    private Long memberId;

    @Column(name = "member_name", length = 32, nullable = false)
    private String memberName;

    //收货人
    @Column(name = "consignee", length = 32, nullable = false)
    private String consignee;

    @Column(name = "phone", length = 32, nullable = false)
    private String phone;

    //省
    @Column(name = "province", length = 32, nullable = false)
    private String province;

    //市
    @Column(name = "city", length = 32, nullable = false)
    private String city;

    //区
    @Column(name = "district", length = 32, nullable = false)
    private String district;

    //收货详细地址
    @Column(name = "address", length = 500, nullable = false)
    private String address;

    //会员优惠券id
    @Column(name = "member_coupon_id", length = 20)
    private Long memberCouponId;

    //优惠券抵扣金额
    @Column(name = "deduct_coupon_money", precision=10, scale=2)
    private BigDecimal deductCouponMoney;

    //快递单号
    @Column(name = "express_no", length = 32)
    private String expressNo;

    //运费
    @Column(name = "freight", precision=10, scale=2)
    private BigDecimal freight;

    //订单完成时间
    @Column(name = "finish_time")
    private Date finishTime;

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
