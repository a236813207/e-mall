package com.ken.mall.mapper.dic;

import com.ken.mall.entity.dic.Ad;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Ken
 * @date 2020/3/21
 * @description
 */
public interface AdMapper {

    /**
     * 查找广告列表
     * @param adPositionId
     * @return
     */
    List<Ad> findAdsByAdPositionId(@Param("adPositionId") Integer adPositionId);

}
