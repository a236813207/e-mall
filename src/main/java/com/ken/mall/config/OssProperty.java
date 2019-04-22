package com.ken.mall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
@Component
public class OssProperty {

    @Value("${aliyun_accessKeyId}")
    public String accessKeyId;
    @Value("${aliyun_accessKeySecret}")
    public String accessKeySecret;
    @Value("${aliyun_bucketName}")
    public String bucketName;
    @Value("${aliyun_endpoint}")
    public String endpoint;

    public static OssProperty ossProperty = new OssProperty();

    @PostConstruct
    public void init(){
        ossProperty.setAccessKeyId(accessKeyId);
        ossProperty.setAccessKeySecret(accessKeySecret);
        ossProperty.setBucketName(bucketName);
        ossProperty.setEndpoint(endpoint);
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
