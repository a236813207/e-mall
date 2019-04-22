package com.ken.mall.dao;


import com.wwbetter.service.pojo.search.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.LockModeType;
import java.io.Serializable;
import java.util.List;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
@NoRepositoryBean
public interface GenericRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    boolean isManaged(T entity);

    ID getIdentifier(T entity);

    List<T> findList(Pageable pageable, List<Filter> filters, List<Sort.Order> orders);

    long count(Filter... filters);

    Page<T> findPage(Pageable pageable, List<Filter> filters, List<Sort.Order> orders);

    void lock(T entity, LockModeType lockModeType);

    void refresh(T entity, LockModeType lockModeType);

    T update(T eneity);
}
