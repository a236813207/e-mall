/*
 *

 *
 */
package com.ken.mall.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

/**
 * Entity - 排序基类
 *
 * @version 1.0
 */
@MappedSuperclass
public abstract class OrderEntity extends BaseEntity implements Comparable<OrderEntity> {

    private static final long serialVersionUID = 5995013015967525827L;

    /**
     * "排序"属性名称
     */
    public static final String ORDER_PROPERTY_NAME = "orders";

    /**
     * 排序
     */
    @Min(0)
    @Column(name = "orders")
    private Integer orders;

    /**
     * 获取排序
     *
     * @return 排序
     */
    public Integer getOrders() {
        return orders;
    }

    /**
     * 设置排序
     *
     * @param orders 排序
     */
    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    /**
     * 实现compareTo方法
     *
     * @param orderEntity 排序对象
     * @return 比较结果
     */
    @Transient
    public int compareTo(OrderEntity orderEntity) {
        return this.orders - orderEntity.getOrders();
    }

}