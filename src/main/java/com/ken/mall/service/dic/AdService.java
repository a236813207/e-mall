package com.ken.mall.service.dic;

import com.ken.mall.entity.dic.Ad;
import com.ken.mall.service.base.BaseService;

import java.util.List;

/**
 * @author Ken
 * @date 2020/3/21
 * @description
 */
public interface AdService extends BaseService<Ad, Long> {

    List<Ad> findByAdPositionId(Integer adPositionId);


}
