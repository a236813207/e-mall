package com.ken.mall.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.Optional;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
@MappedSuperclass
public abstract class CascadeEntityAbstract extends AbstractOrderEntity {
    public static final String PARENT_PROPERTY_NAME = "parentId";
    public static final String PATH_PROPERTY_NAME = "parentIds";

    @Column(name = "parent_id", columnDefinition="bigint(20) not null comment '父级id'")
    private Long parentId;
    @Column(name = "parent_ids", columnDefinition="varchar(1000) not null comment '父级id集合'")
    private String parentIds;
    @Column(name = "name", columnDefinition="varchar(64) not null comment '资源名称'")
    private String name;
    @Column(name = "icon", columnDefinition="varchar(64) not null comment 'icon'")
    private String icon;
    @Column(name = "url", columnDefinition="varchar(64) not null comment 'url'")
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    @Transient
    public String makeSelfAsParentsIds() {
        return Optional.ofNullable(getParentIds()).orElse("") + getId() + "/";
    }


}
