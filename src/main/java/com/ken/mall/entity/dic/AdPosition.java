package com.ken.mall.entity.dic;

import com.ken.mall.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ken
 * @date 2019/7/28
 * @description
 */
@Entity
@Table(name = "dic_ad_postion")
@Getter
@Setter
public class AdPosition extends BaseEntity {

    @Column(name = "name", columnDefinition="varchar(32) not null comment '广告位置名称'")
    private String name;

    @Column(name = "width", columnDefinition="tinyint(4) not null comment '广告宽度'")
    private Integer width;

    @Column(name = "height", columnDefinition="tinyint(4) not null comment '广告高度'")
    private Integer height;

    @Column(name = "memo", columnDefinition="varchar(255) default null comment '备注'")
    private String memo;
}
