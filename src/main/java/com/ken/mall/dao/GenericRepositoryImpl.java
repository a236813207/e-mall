package com.ken.mall.dao;

import com.ken.mall.entity.CascadeEntity;
import com.ken.mall.entity.LogicDeleteable;
import com.ken.mall.entity.OrderEntity;
import com.ken.mall.pojo.search.Filter;
import org.dom4j.tree.AbstractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
public class GenericRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements GenericRepository<T, ID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericRepositoryImpl.class);

    /**
     * 别名数
     */
    private static volatile long aliasCount = 0;

    private final EntityManager entityManager;

    private Class<T> entityClass;

    private String idName;

    public GenericRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityClass = entityInformation.getJavaType();
        this.idName = entityInformation.getIdAttributeNames().iterator().next();
    }

    public GenericRepositoryImpl(Class<T> domainClass, EntityManager em) {
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
    }

    /**
     * 根据主键删除相应实体
     *
     * @param id 主键
     */
    @Override
    public void deleteById(ID id) {
        Optional m = findById(id);
        delete((T)m.get());
    }

    /**
     * 删除实体
     *
     * @param m 实体
     */
    @Override
    public void delete(final T m) {
        if (m == null) {
            return;
        }
        if (m instanceof LogicDeleteable) {
            ((LogicDeleteable) m).markDeleted();
            save(m);
        } else {
            super.delete(m);
        }
    }


    @Override
    public boolean isManaged(T entity) {
        return entityManager.contains(entity);
    }

    @Override
    public ID getIdentifier(T entity) {
        Assert.notNull(entity, "entity is empty");
        return (ID) entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
    }

    @Override
    public List<T> findList(Pageable pageable, List<Filter> filters, List<Sort.Order> orders) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return findList(criteriaQuery, pageable, filters, orders);
    }

    @Override
    public long count(Filter... filters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return count(criteriaQuery, filters != null ? Arrays.asList(filters) : null);
    }

    @Override
    public Page<T> findPage(Pageable pageable, List<Filter> filters, List<Sort.Order> orders) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return findPage(criteriaQuery, filters, orders, pageable);
    }


    protected List<T> findList(CriteriaQuery<T> criteriaQuery, Pageable pageable, List<Filter> filters, List<Sort.Order> orders) {
        Assert.notNull(criteriaQuery, "criteriaQuery is null");
        Assert.notNull(criteriaQuery.getSelection(), "criteriaQuery.getSelection() is null");
        Assert.notEmpty(criteriaQuery.getRoots(), "criteriaQuery.getRoots() is null");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Root<T> root = getRoot(criteriaQuery);
        addRestrictions(criteriaQuery, filters);
        addOrders(criteriaQuery, orders);
        if (criteriaQuery.getOrderList().isEmpty()) {
            if (CascadeEntity.class.isAssignableFrom(entityClass)) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(CascadeEntity.PATH_PROPERTY_NAME)),
                        criteriaBuilder.asc(root.get(OrderEntity.ORDER_PROPERTY_NAME)));
            } else if (OrderEntity.class.isAssignableFrom(entityClass)) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(OrderEntity.ORDER_PROPERTY_NAME)));
            } else if (AbstractEntity.class.isAssignableFrom(entityClass)) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(OrderEntity.CREATE_DATE_PROPERTY_NAME)));
            }
        }
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT);
        if (pageable != null) {
            query.setFirstResult((int)pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }

        return query.getResultList();
    }


    protected Page<T> findPage(CriteriaQuery<T> criteriaQuery, List<Filter> filters, List<Sort.Order> orders, Pageable pageable) {
        Assert.notNull(criteriaQuery, "");
        Assert.notNull(criteriaQuery.getSelection(), "");
        Assert.notEmpty(criteriaQuery.getRoots(), "");

        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Root<T> root = getRoot(criteriaQuery);
        addRestrictions(criteriaQuery, filters);
        addOrders(criteriaQuery, orders);
        if (criteriaQuery.getOrderList().isEmpty()) {
            if (CascadeEntity.class.isAssignableFrom(entityClass)) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(CascadeEntity.PATH_PROPERTY_NAME)),
                        criteriaBuilder.asc(root.get(OrderEntity.ORDER_PROPERTY_NAME)));
            } else if (OrderEntity.class.isAssignableFrom(entityClass)) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(OrderEntity.ORDER_PROPERTY_NAME)));
            } else if (AbstractEntity.class.isAssignableFrom(entityClass)) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(OrderEntity.CREATE_DATE_PROPERTY_NAME)));
            }
        }
        long total = count(criteriaQuery, null);
        //int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT);
        query.setFirstResult((int)pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        return new PageImpl<T>(query.getResultList(), pageable, total);
    }


    protected Long count(CriteriaQuery<T> criteriaQuery, List<Filter> filters) {
        Assert.notNull(criteriaQuery, "");
        Assert.notNull(criteriaQuery.getSelection(), "");
        Assert.notEmpty(criteriaQuery.getRoots(), "");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> countCriteriaQuery = criteriaBuilder.createQuery(Long.class);
        addRestrictions(criteriaQuery, filters);
        for (Root<?> root : criteriaQuery.getRoots()) {
            Root<?> dest = countCriteriaQuery.from(root.getJavaType());
            dest.alias(getAlias(root));
            copyJoins(root, dest);
        }

        Root<?> countRoot = getRoot(countCriteriaQuery, criteriaQuery.getResultType());
        countCriteriaQuery.select(criteriaBuilder.count(countRoot));

        if (criteriaQuery.getGroupList() != null && !criteriaQuery.getGroupList().isEmpty()) {
            countCriteriaQuery.groupBy(criteriaQuery.getGroupList());
        }
        if (criteriaQuery.getGroupRestriction() != null) {
            countCriteriaQuery.having(criteriaQuery.getGroupRestriction());
        }
        if (criteriaQuery.getRestriction() != null) {
            countCriteriaQuery.where(criteriaQuery.getRestriction());
        }
        return entityManager.createQuery(countCriteriaQuery).setFlushMode(FlushModeType.COMMIT).getSingleResult();
    }


    private synchronized String getAlias(Selection<?> selection) {
        if (selection != null) {
            String alias = selection.getAlias();
            if (alias == null) {
                if (aliasCount >= 1000) {
                    aliasCount = 0;
                }
                alias = "shopGeneratedAlias" + aliasCount++;
                selection.alias(alias);
            }
            return alias;
        }
        return null;
    }

    private void copyJoins(From<?, ?> from, From<?, ?> to) {
        for (Join<?, ?> join : from.getJoins()) {
            Join<?, ?> toJoin = to.join(join.getAttribute().getName(), join.getJoinType());
            toJoin.alias(getAlias(join));
            copyJoins(join, toJoin);
        }
        for (Fetch<?, ?> fetch : from.getFetches()) {
            Fetch<?, ?> toFetch = to.fetch(fetch.getAttribute().getName());
            copyFetches(fetch, toFetch);
        }
    }

    private void copyFetches(Fetch<?, ?> from, Fetch<?, ?> to) {
        for (Fetch<?, ?> fetch : from.getFetches()) {
            Fetch<?, ?> toFetch = to.fetch(fetch.getAttribute().getName());
            copyFetches(fetch, toFetch);
        }
    }

    private Root<T> getRoot(CriteriaQuery<T> criteriaQuery) {
        if (criteriaQuery != null) {
            return getRoot(criteriaQuery, criteriaQuery.getResultType());
        }
        return null;
    }


    private Root<T> getRoot(CriteriaQuery<?> criteriaQuery, Class<T> clazz) {
        if (criteriaQuery != null && criteriaQuery.getRoots() != null && clazz != null) {
            for (Root<?> root : criteriaQuery.getRoots()) {
                if (clazz.equals(root.getJavaType())) {
                    return (Root<T>) root.as(clazz);
                }
            }
        }
        return null;
    }


    private void addRestrictions(CriteriaQuery<T> criteriaQuery, List<Filter> filters) {
        if (criteriaQuery == null || filters == null || filters.isEmpty()) {
            return;
        }
        Root<T> root = getRoot(criteriaQuery);
        if (root == null) {
            return;
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Predicate restrictions = criteriaQuery.getRestriction() != null ? criteriaQuery.getRestriction() : criteriaBuilder.conjunction();
        for (Filter filter : filters) {
            if (filter == null || StringUtils.isEmpty(filter.getProperty())) {
                continue;
            }
            if (filter.getOperator() == Filter.Operator.eq && filter.getValue() != null) {
                if (filter.getIgnoreCase() != null && filter.getIgnoreCase() && filter.getValue() instanceof String) {
                    restrictions = criteriaBuilder.and(restrictions,
                            criteriaBuilder.equal(criteriaBuilder.lower(root.get(filter.getProperty())),
                                    ((String) filter.getValue()).toLowerCase()));
                } else {
                    restrictions = criteriaBuilder.and(restrictions,
                            criteriaBuilder.equal(root.get(filter.getProperty()), filter.getValue()));
                }
            } else if (filter.getOperator() == Filter.Operator.ne && filter.getValue() != null) {
                if (filter.getIgnoreCase() != null && filter.getIgnoreCase() && filter.getValue() instanceof String) {
                    restrictions = criteriaBuilder.and(restrictions,
                            criteriaBuilder.notEqual(criteriaBuilder.lower(root.get(filter.getProperty())),
                                    ((String) filter.getValue()).toLowerCase()));
                } else {
                    restrictions = criteriaBuilder.and(restrictions,
                            criteriaBuilder.notEqual(root.get(filter.getProperty()), filter.getValue()));
                }
            } else if (filter.getOperator() == Filter.Operator.gt && filter.getValue() != null) {
                restrictions = criteriaBuilder.and(restrictions,
                        criteriaBuilder.gt(root.get(filter.getProperty()), (Number) filter.getValue()));
            } else if (filter.getOperator() == Filter.Operator.lt && filter.getValue() != null) {
                restrictions = criteriaBuilder.and(restrictions,
                        criteriaBuilder.lt(root.get(filter.getProperty()), (Number) filter.getValue()));
            } else if (filter.getOperator() == Filter.Operator.ge && filter.getValue() != null) {
                restrictions = criteriaBuilder.and(restrictions,
                        criteriaBuilder.ge(root.get(filter.getProperty()), (Number) filter.getValue()));
            } else if (filter.getOperator() == Filter.Operator.le && filter.getValue() != null) {
                restrictions = criteriaBuilder.and(restrictions,
                        criteriaBuilder.le(root.get(filter.getProperty()), (Number) filter.getValue()));
            } else if (filter.getOperator() == Filter.Operator.like && filter.getValue() != null && filter.getValue() instanceof String) {
                restrictions = criteriaBuilder.and(restrictions,
                        criteriaBuilder.like(root.get(filter.getProperty()), (String) filter.getValue()));
            } else if (filter.getOperator() == Filter.Operator.in && filter.getValue() != null) {
                Object items = filter.getValue();
                CriteriaBuilder.In<Object> inObject = criteriaBuilder.in(root.get(filter.getProperty()));
                if(items.getClass().isArray()){
                    int length = Array.getLength(items);
                    for(int i=0;i<length;i++){
                        inObject.value(Array.get(items,i));
                    }
                }else if(items instanceof Collection){
                    Collection collection = (Collection) items;
                    for (Object value : collection) {
                        inObject.value(value);
                    }
                }
                restrictions = criteriaBuilder.and(restrictions,
                        inObject);
            } else if (filter.getOperator() == Filter.Operator.isNull) {
                restrictions = criteriaBuilder.and(restrictions,
                        root.get(filter.getProperty()).isNull());
            } else if (filter.getOperator() == Filter.Operator.isNotNull) {
                restrictions = criteriaBuilder.and(restrictions,
                        root.get(filter.getProperty()).isNotNull());
            }
        }
        criteriaQuery.where(restrictions);
    }

    private void addOrders(CriteriaQuery<T> criteriaQuery, List<Sort.Order> orders) {
        if (criteriaQuery == null || orders == null || orders.isEmpty()) {
            return;
        }
        Root<T> root = getRoot(criteriaQuery);
        if (root == null) {
            return;
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        List<Order> orderList = new ArrayList<Order>();
        if (!criteriaQuery.getOrderList().isEmpty()) {
            orderList.addAll(criteriaQuery.getOrderList());
        }
        for (Sort.Order order : orders) {
            if (order.getDirection() == Sort.Direction.ASC) {
                orderList.add(criteriaBuilder.asc(root.get(order.getProperty())));
            } else if (order.getDirection() == Sort.Direction.DESC) {
                orderList.add(criteriaBuilder.desc(root.get(order.getProperty())));
            }
        }
        criteriaQuery.orderBy(orderList);
    }

    @Override
    public void lock(T entity, LockModeType lockModeType) {
        if (entity != null && lockModeType != null) {
            entityManager.lock(entity, lockModeType);
        }
    }

    public void refresh(T entity, LockModeType lockModeType) {
        if (entity != null) {
            if (lockModeType != null) {
                entityManager.refresh(entity, lockModeType);
            } else {
                entityManager.refresh(entity);
            }
        }
    }

    @Override
    public T update(T eneity) {
        return this.entityManager.merge(eneity);
    }
}
