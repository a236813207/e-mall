package com.ken.mall.service.dic.impl;

import com.ken.mall.entity.dic.Ad;
import com.ken.mall.mapper.dic.AdMapper;
import com.ken.mall.service.base.impl.BaseServiceImpl;
import com.ken.mall.service.dic.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ken
 * @date 2020/3/21
 * @description
 */
@Service
public class AdServiceImpl extends BaseServiceImpl<Ad, Long> implements AdService {

    @Autowired
    private AdMapper adMapper;

    @Override
    public List<Ad> findByAdPositionId(Integer adPositionId) {
        return this.adMapper.findAdsByAdPositionId(adPositionId);
    }
}
