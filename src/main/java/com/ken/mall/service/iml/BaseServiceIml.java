package com.ken.mall.service.iml;

import com.ken.mall.dao.GenericRepository;
import com.ken.mall.entity.AbstractEntity;
import com.ken.mall.pojo.search.Filter;
import com.ken.mall.pojo.search.OrderBo;
import com.ken.mall.pojo.search.PageBo;
import com.ken.mall.pojo.search.PageRequestBo;
import com.ken.mall.service.BaseService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
@Transactional
public abstract class BaseServiceIml<T, ID extends Serializable> implements BaseService<T, ID> {

    /**
     * 更新忽略属性
     */
    private static final String[] UPDATE_IGNORE_PROPERTIES = new String[]{AbstractEntity.ID_PROPERTY_NAME, AbstractEntity.CREATE_DATE_PROPERTY_NAME, AbstractEntity.MODIFY_DATE_PROPERTY_NAME};

    @Autowired
    private GenericRepository<T, ID> repository;

    protected GenericRepository<T, ID> getRepository() {
        return repository;
    }

    @Override
    @Transactional(readOnly = true)
    public T find(ID id) {
        return this.repository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return this.repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findList(ID... ids) {
        List<T> result = new ArrayList<T>();
        if (ids != null) {
            for (ID id : ids) {
                T entity = find(id);
                if (entity != null) {
                    result.add(entity);
                }
            }
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findTopList(Integer count, List<Filter> filters, List<OrderBo> orders) {
        PageRequestBo pageableBo = new PageRequestBo();
        pageableBo.setRows(count);
        return findList(pageableBo, filters, orders);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findList(PageRequestBo page, List<Filter> filters, List<OrderBo> orders) {
        List<Sort.Order> orderList = convertOrders(orders);
        Pageable pageable = convertPageable(page);
        return this.repository.findList(pageable, filters, orderList);
    }


    @Override
    @Transactional(readOnly = true)
    public PageBo<T> findPage(PageRequestBo page, List<Filter> filters, List<OrderBo> orders) {
        Pageable pageable = convertPageable(page);
        List<Sort.Order> orderList = convertOrders(orders);
        Page<T> pageResult = this.repository.findPage(pageable, filters, orderList);
        PageBo<T> result = new PageBo<>(pageResult.getContent(), pageResult.getTotalElements(), page);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return count(new Filter[]{});
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Filter... filters) {
        return repository.count(filters);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(ID id) {
        return find(id) != null;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Filter... filters) {
        return count(filters) > 0;
    }

    @Override
    public T save(T entity) {
        return this.repository.save(entity);
    }

    @Override
    public T update(T entity) {
        return this.repository.save(entity);
    }

    @Override
    public T update(T entity, String... ignoreProperties) {
        if (repository.isManaged(entity)) {
            throw new IllegalArgumentException("Entity must not be managed");
        }
        T persistant = this.find(repository.getIdentifier(entity));

        if (persistant != null) {
            copyProperties(entity, persistant, ArrayUtils.addAll(ignoreProperties, UPDATE_IGNORE_PROPERTIES));
            return update(persistant);
        } else {
            return update(entity);
        }
    }

    @Override
    public void delete(ID id) {
        this.repository.deleteById(id);
    }

    @Override
    public void delete(ID... ids) {
        if (ids != null) {
            for (ID id : ids) {
                delete(this.find(id));
            }
        }
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }


    private List<Sort.Order> convertOrders(List<OrderBo> orders) {
        if (CollectionUtils.isEmpty(orders)) {
            return null;
        }
        List<Sort.Order> list = new ArrayList<>();
        for (OrderBo bo : orders) {
            if (bo.getDirection() == OrderBo.Direction.asc) {
                list.add(new Sort.Order(Sort.Direction.ASC, bo.getProperty()));
                continue;
            }
            if (bo.getDirection() == OrderBo.Direction.desc) {
                list.add(new Sort.Order(Sort.Direction.DESC, bo.getProperty()));
            }
        }
        return list;
    }

    protected Pageable convertPageable(PageRequestBo page) {
        if (page == null) {
            return null;
        }
        int startPage = page.getPage() - 1;
        if (startPage < 0) {
            startPage = 0;
        }
        return new PageRequest(startPage, page.getRows());
    }


    private void copyProperties(Object source, Object target, String[] ignoreProperties) throws BeansException {
        Assert.notNull(source, "Source must not  null");
        Assert.notNull(target, "Target must not  null");

        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(target.getClass());
        List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;
        for (PropertyDescriptor targetPd : targetPds) {
            if (targetPd.getWriteMethod() != null && (ignoreProperties == null || (!ignoreList.contains(targetPd.getName())))) {
                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null && sourcePd.getReadMethod() != null) {
                    try {
                        Method readMethod = sourcePd.getReadMethod();
                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                            readMethod.setAccessible(true);
                        }
                        Object sourceValue = readMethod.invoke(source);
                        Object targetValue = readMethod.invoke(target);
                        if (sourceValue != null && targetValue != null && targetValue instanceof Collection) {
                            Collection collection = (Collection) targetValue;
                            collection.clear();
                            collection.addAll((Collection) sourceValue);
                        } else {
                            Method writeMethod = targetPd.getWriteMethod();
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, sourceValue);
                        }
                    } catch (Throwable ex) {
                        throw new FatalBeanException("Could not copy properties from source to target", ex);
                    }
                }
            }
        }
    }
}
