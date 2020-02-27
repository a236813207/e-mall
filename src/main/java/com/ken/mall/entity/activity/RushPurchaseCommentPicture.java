package com.ken.mall.entity.activity;

import com.ken.mall.entity.AbstractOrderEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ken
 * @date 2020/2/9
 * @description 抢购活动评价图
 */
@Entity
@Table(name = "rush_purchase_comment_picture")
@Getter
@Setter
public class RushPurchaseCommentPicture extends AbstractOrderEntity {

    @Column(name = "comment_id", columnDefinition="bigint(20) not null comment '评价id'")
    private Long commentId;

    @Column(name = "img_url", columnDefinition="varchar(256) not null comment '图片地址'")
    private String imgUrl;

}
