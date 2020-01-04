package com.ken.mall.strategy.impl;

import com.ken.mall.strategy.SmsStrategy;
import com.ken.mall.utils.ClientUtils;
import com.ken.mall.utils.MD5Util;
import com.ken.mall.web.bind.response.ResBody;
import org.apache.http.Consts;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ken
 * @date 2020/1/1
 * @description 天一泓国际短信 https://www.sms0755.com/api.html
 */
public class IMFSSmsStrategy implements SmsStrategy {

    private static final Logger logger = LoggerFactory.getLogger(IMFSSmsStrategy.class);

    @Value("${imfs.account}")
    private String account;

    @Value("${imfs.password}")
    private String password;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * http://sms.skylinelabs.cc:20003/sendsmsV2?account=***&sign=***&datetime=***&senderid=***&numbers=10010,1008611&content=***
     * @param phone
     * @param code
     * @param template
     * @return
     */
    @Override
    public ResBody sendMsg(String phone, String code, String template) {
        try {
            template = URLEncoder.encode(String.format(template, code), Consts.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("send msg template urlencode error");
        }
        String datetime = sdf.format(new Date());
        String sign = createSign(datetime);
        StringBuffer url = new StringBuffer("http://sms.skylinelabs.cc:20003/sendsmsV2");
        url.append("?account=").append(account).append("&sign=")
                .append(sign).append("&datetime=").append(datetime)
                .append("&numbers=").append(phone).append("&content=").append(template);
        HttpPost post= new HttpPost(url.toString());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("numbers",phone);
        jsonObject.put("content",template);

        StringEntity params =new StringEntity(jsonObject.toString(), Consts.UTF_8);
        params.setContentEncoding(Consts.UTF_8.toString());
        params.setContentType("application/json;charset=utf-8");
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(500).build();
        post.setConfig(requestConfig);
        post.setEntity(params);
        String result = ClientUtils.send(post);
        logger.debug("send msg result:", result);
        JSONObject resultJson = new JSONObject(result);

        if (resultJson.getInt("status") == 0) {
            return ResBody.success();
        }
        return ResBody.failure();
    }


    /**
     * 使用 账号+密码+时间 生成MD5字符串作为签名。MD5生成32位，且需要小写
     * 例如：
     * 账号是account
     * 密码是pwd
     * 时间是20181109231202
     * 就需要用accountpwd20181109231202
     * 来生成MD5的签名，生成的签名为
     * ec971c60092c2514826a3d64f53356e2
     * @return
     */
    private String createSign(String datetime) {
        String str = account+password+datetime;
        return MD5Util.string2MD5(str).toLowerCase();
    }
}
