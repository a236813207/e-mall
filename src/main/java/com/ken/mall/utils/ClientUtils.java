package com.ken.mall.utils;

import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Ken
 * @date 2020/1/1
 * @description
 */
public class ClientUtils {

    private static Logger logger = LoggerFactory.getLogger(ClientUtils.class);

    /**
     * 发送url请求
     * @param request
     * @return
     * @throws Exception
     */
    public static String send(HttpUriRequest request){
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = client.execute(request);
            String result = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            logger.debug(result);
            return result;
        } catch(JSONException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("服务器异常，请稍后再试！", e);
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            try {
                if(response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
            try {
                if(client != null) {
                    client.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

}
