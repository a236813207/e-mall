package com.ken.mall.service.shop;

import com.ken.mall.entity.goods.Goods;
import com.ken.mall.pojo.search.PageBo;
import com.ken.mall.pojo.search.PageRequestBo;
import com.ken.mall.pojo.shop.GoodsDetailVo;
import com.ken.mall.pojo.shop.GoodsListVo;
import com.ken.mall.service.base.BaseService;

/**
 * @author Ken
 * @date 2020/3/29
 * @description
 */
public interface ShopService extends BaseService<Goods, Long> {

    PageBo<GoodsListVo> findGoodsPage(PageRequestBo page);

    GoodsDetailVo detail(Long id);
}
