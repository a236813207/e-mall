package com.ken.mall.entity.dic;

import com.ken.mall.entity.AbstractOrderEntity;
import com.ken.mall.entity.dic.enums.AdTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Ken
 * @date 2018-11-02
 * @description
 */
@Entity
@Table(name = "dic_ad")
@Getter
@Setter
public class Ad extends AbstractOrderEntity {

    @Column(name = "ad_postion_id", columnDefinition="bigint(20) not null comment '广告位置id'")
    private Long adPostionId;

    @Column(name = "type", columnDefinition="varchar(32) not null comment '广告类型'")
    @Enumerated(EnumType.STRING)
    private AdTypeEnum type;

    @Column(name = "name", columnDefinition="varchar(32) not null comment '广告名字'")
    private String name;

    @Column(name = "url", columnDefinition="varchar(255) default null comment 'url'")
    private String url;

    @Column(name = "link", columnDefinition="varchar(255) default null comment '跳转地址'")
    private String link;

    @Column(name = "content", columnDefinition="varchar(255) default null comment '内容'")
    private String content;

    @Column(name = "start_time", columnDefinition="datetime not null comment '起始时间'")
    private Date startTime;

    @Column(name = "end_time", columnDefinition="datetime not null comment '结束时间'")
    private Date endTime;

    @Column(name = "is_enabled", columnDefinition="tinyint(1) not null comment '是否可用'")
    private Boolean isEnabled;
}
