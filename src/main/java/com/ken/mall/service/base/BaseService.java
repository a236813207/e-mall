package com.ken.mall.service.base;


import com.ken.mall.exception.BizException;
import com.ken.mall.pojo.search.Filter;
import com.ken.mall.pojo.search.OrderBo;
import com.ken.mall.pojo.search.PageBo;
import com.ken.mall.pojo.search.PageRequestBo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
public interface BaseService<T, ID extends Serializable> {
    /**
     * 查找实体对象
     *
     * @param id ID
     * @return 实体对象，若不存在则返回null
     */
    T find(ID id) throws BizException;

    /**
     * 查找所有实体对象集合
     *
     * @return 所有实体对象集合
     */
    List<T> findAll() throws BizException;

    /**
     * 查找实体对象集合
     *
     * @param ids ID
     * @return 实体对象集合
     */
    @SuppressWarnings("unchecked")
    List<T> findList(ID... ids) throws BizException;

    /**
     * 查找实体对象集合
     *
     * @param count   数量
     * @param filters 筛选
     * @param orders  排序
     * @return 实体对象集合
     */
    List<T> findTopList(Integer count, List<Filter> filters, List<OrderBo> orders) throws BizException;

    /**
     * 查找实体对象集合
     *
     * @param page    分页信息
     * @param filters 筛选
     * @param orders  排序
     * @return 实体对象集合
     */
    List<T> findList(PageRequestBo page, List<Filter> filters, List<OrderBo> orders) throws BizException;

    /**
     * 查找实体对象分页
     *
     * @param page    分页信息
     * @param filters 筛选
     * @param orders  排序
     * @return 实体对象分页
     */
    PageBo<T> findPage(PageRequestBo page, List<Filter> filters, List<OrderBo> orders) throws BizException;

    /**
     * 查询实体对象总数
     *
     * @return 实体对象总数
     */
    long count() throws BizException;

    /**
     * 查询实体对象数量
     *
     * @param filters 筛选
     * @return 实体对象数量
     */
    long count(Filter... filters) throws BizException;

    /**
     * 判断实体对象是否存在
     *
     * @param id ID
     * @return 实体对象是否存在
     */
    boolean exists(ID id) throws BizException;

    /**
     * 判断实体对象是否存在
     *
     * @param filters 筛选
     * @return 实体对象是否存在
     */
    boolean exists(Filter... filters) throws BizException;

    /**
     * 保存实体对象
     *
     * @param entity 实体对象
     */
    T save(T entity) throws BizException;

    /**
     * 更新实体对象
     *
     * @param entity 实体对象
     * @return 实体对象
     */
    T update(T entity) throws BizException;

    /**
     * 更新实体对象
     *
     * @param entity           实体对象
     * @param ignoreProperties 忽略属性
     * @return 实体对象
     */
    T update(T entity, String... ignoreProperties) throws BizException;

    /**
     * 删除实体对象
     *
     * @param id ID
     */
    void delete(ID id) throws BizException;

    /**
     * 删除实体对象
     *
     * @param ids ID
     */
    @SuppressWarnings("unchecked")
    void delete(ID... ids) throws BizException;

    /**
     * 删除实体对象
     *
     * @param entity 实体对象
     */
    void delete(T entity) throws BizException;


}
