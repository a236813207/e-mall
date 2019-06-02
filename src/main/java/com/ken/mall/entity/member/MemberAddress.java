package com.ken.mall.entity.member;

import com.ken.mall.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "member_address")
@Getter
@Setter
public class MemberAddress extends BaseEntity {

    @Column(name = "member_id", nullable = false, length = 20)
    private Long memberId;

    @Column(name = "consignee", nullable = false, length = 32)
    private String consignee;

    @Column(nullable = false, length = 32)
    private String province;

    @Column(nullable = false, length = 64)
    private String city;

    @Column(nullable = false, length = 64)
    private String district;

    @Column(nullable = false, length = 128)
    private String address;

    @Column(nullable = false, length = 32)
    private String phone;

    @Column(nullable = false)
    private Boolean isDefault;

}
