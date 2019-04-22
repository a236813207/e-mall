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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
