package com.ken.mall.dao.shop;

import com.ken.mall.dao.GenericRepository;
import com.ken.mall.entity.goods.GoodsCarousel;

import java.util.List;

/**
 * @author Ken
 * @date 2020/3/30
 * @description
 */
public interface GoodsCarouselDao extends GenericRepository<GoodsCarousel, Long> {

    List<GoodsCarousel> findByGoodsIdOrderByOrdersAsc(Long id);
}
