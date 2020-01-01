package com.ken.mall.strategy.impl;

import com.ken.mall.strategy.SmsStrategy;
import com.ken.mall.web.bind.response.ResBody;

/**
 * @author Ken
 * @date 2020/1/1
 * @description 天一泓国际短信
 */
public class IMFSSmsStrategy implements SmsStrategy {

    @Override
    public ResBody sendMsg(String phone, String code, String template) {
        return null;
    }
}
