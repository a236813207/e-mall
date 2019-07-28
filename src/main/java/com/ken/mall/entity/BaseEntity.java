package com.ken.mall.entity;

import javax.persistence.*;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
@MappedSuperclass
public abstract class BaseEntity extends AbstractEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 20, insertable = false, updatable = false)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
