package com.ken.mall.config;

import com.aliyun.oss.OSSClient;
import com.ken.mall.utils.oss.OSSClientFactory;
import com.ken.mall.utils.oss.OSSManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
@Configuration
public class OssConfig {


    @Bean
    public OSSManager createOssManager(OSSClient ossClient, @Value("${aliyun_bucketName}") String bucketsName,
                                       @Value("${aliyun_endpoint}") String aliyunEndpoint) {
        Map<String, String> buckets = new HashMap<>();
        buckets.put(bucketsName, "http://" + bucketsName + "." + aliyunEndpoint);
        return new OSSManager(ossClient, buckets);
    }

    @Bean
    public OSSClient createOssClient(@Value("${aliyun_accessKeyId}")String keyId,
                                     @Value("${aliyun_endpoint}") String aliyunEndpoint,
                                     @Value("${aliyun_accessKeySecret}")String keySecret){
        return OSSClientFactory.getOSSClient(aliyunEndpoint,keyId,keySecret);
    }
}
