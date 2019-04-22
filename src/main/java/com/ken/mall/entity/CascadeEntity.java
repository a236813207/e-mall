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
public abstract class CascadeEntity extends OrderEntity {
    public static final String PARENT_PROPERTY_NAME = "parentId";
    public static final String PATH_PROPERTY_NAME = "parentIds";

    //上级分类ID
    private Long parentId;
    @Column(length = 1000)
    private String parentIds;
    private String name;
    private String icon;
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
