package com.ken.mall.entity;

import javax.persistence.*;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
@EntityListeners(EntityListener.class)
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {

    /**
     * "ID"属性名称
     */
    public static final String ID_PROPERTY_NAME = "id";

    /**
     * "创建日期"属性名称
     */
    public static final String CREATE_DATE_PROPERTY_NAME = "createTime";

    /**
     * "修改日期"属性名称
     */
    public static final String MODIFY_DATE_PROPERTY_NAME = "modifyTime";

    /**
     * 保存验证组
     */
    public interface Save extends Default {

    }

    /**
     * 更新验证组
     */
    public interface Update extends Default {

    }

    /**
     * 创建日期
     */
    @Column(nullable = false, updatable = false, name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * 修改日期
     */
    @Column(nullable = false, name = "modify_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;

    public abstract ID getId();

    public abstract void setId(ID id);

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity<?> that = (AbstractEntity<?>) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
