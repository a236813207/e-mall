package com.ken.mall.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
public class EntityListener {

    @PrePersist
    public void prePersist(AbstractEntity entity){
        Date date = new Date();
        entity.setCreateTime(date);
        entity.setModifyTime(date);
    }


    @PreUpdate
    public void preUpdate(AbstractEntity entity){
        Date date = new Date();
        entity.setModifyTime(date);
    }

}
