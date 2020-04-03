package com.ken.mall.service.shop.impl;

import com.google.common.collect.Lists;
import com.ken.mall.dao.shop.GoodsCarouselDao;
import com.ken.mall.dao.shop.GoodsSpecificationDao;
import com.ken.mall.entity.goods.Goods;
import com.ken.mall.entity.goods.GoodsCarousel;
import com.ken.mall.entity.goods.GoodsSpecification;
import com.ken.mall.exception.BizException;
import com.ken.mall.exception.codes.BizCodeFace;
import com.ken.mall.exception.codes.ErrorCode;
import com.ken.mall.pojo.search.Filter;
import com.ken.mall.pojo.search.OrderBo;
import com.ken.mall.pojo.search.PageBo;
import com.ken.mall.pojo.search.PageRequestBo;
import com.ken.mall.pojo.shop.GoodsDetailVo;
import com.ken.mall.pojo.shop.GoodsListVo;
import com.ken.mall.service.base.impl.BaseServiceImpl;
import com.ken.mall.service.shop.ShopService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ken
 * @date 2020/3/29
 * @description
 */
@Service
public class ShopServiceImpl extends BaseServiceImpl<Goods, Long> implements ShopService {

    @Autowired
    private GoodsCarouselDao goodsCarouselDao;
    @Autowired
    private GoodsSpecificationDao goodsSpecificationDao;

    @Override
    public PageBo<GoodsListVo> findGoodsPage(PageRequestBo page) {
        PageBo<Goods> goodsPage = this.findPage(page, Lists.newArrayList(Filter.eq("isOnSale", 1)), Lists.newArrayList(OrderBo.asc("orders")));
        List<Goods> goods = goodsPage.getData();
        if (CollectionUtils.isEmpty(goods)) {
            return new PageBo<>(null, 0, page);
        }
        List<GoodsListVo> goodsList = goods.stream().map(item -> {
            GoodsListVo vo = new GoodsListVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).collect(Collectors.toList());
        return new PageBo<>(goodsList, goodsPage.getRecordsTotal(), page);
    }

    @Override
    public GoodsDetailVo detail(Long id) {
        Goods goods = this.find(id);
        if (goods == null) {
            throw new BizException(BizCodeFace.createBizCode(ErrorCode.DATE_NULL).message("商品不存在"));
        }
        GoodsDetailVo vo = new GoodsDetailVo();
        BeanUtils.copyProperties(goods, vo);
        // 查找轮播图
        List<GoodsCarousel> carousels = this.goodsCarouselDao.findByGoodsIdOrderByOrdersAsc(id);
        vo.setCarousels(carousels);
        // 查找规格
        List<GoodsSpecification> specifications = this.goodsSpecificationDao.findByGoodsIdOrderByOrdersAsc(id);
        vo.setSpecifications(specifications);
        return vo;
    }
}
