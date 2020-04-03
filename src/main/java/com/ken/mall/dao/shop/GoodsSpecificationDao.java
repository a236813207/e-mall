package com.ken.mall.dao.shop;

import com.ken.mall.dao.GenericRepository;
import com.ken.mall.entity.goods.GoodsSpecification;

import java.util.List;

/**
 * @author Ken
 * @date 2020/3/30
 * @description
 */
public interface GoodsSpecificationDao extends GenericRepository<GoodsSpecification, Long> {

    List<GoodsSpecification> findByGoodsIdOrderByOrdersAsc(Long id);
}
