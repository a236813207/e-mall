package com.ken.mall.web.admin.controller;

import com.ken.mall.utils.oss.OSSManager;
import com.ken.mall.web.bind.response.ResBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Ken
 * @date 2020/3/28
 * @description
 */
@RequestMapping("/admin")
@RestController
public class UploadController {

    @Autowired
    private OSSManager ossManager;

    private @Value("${aliyun_bucketName}")
    String bucketsName;

    @PostMapping("/upload")
    public ResBody uploadImage(@RequestParam("file") MultipartFile file, String dir) throws IOException, NoSuchAlgorithmException {
        String url = ossManager.putObject(dir, bucketsName, file);
        return ResBody.success(url);
    }

}
