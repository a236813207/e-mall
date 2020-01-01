package com.ken.mall.strategy;

import com.ken.mall.web.bind.response.ResBody;

/**
 * @author Ken
 * @date 2020/1/1
 * @description
 */
public interface SmsStrategy {

    ResBody sendMsg(String phone, String code, String template);

}
