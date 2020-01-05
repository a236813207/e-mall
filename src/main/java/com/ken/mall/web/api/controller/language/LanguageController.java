package com.ken.mall.web.api.controller.language;

import com.ken.mall.web.bind.response.ResBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ken
 * @date 2020/1/6
 * @description
 */
@RestController
@RequestMapping("/api")
@Api(tags = "多语言")
public class LanguageController {

    @GetMapping("/lang")
    @ApiOperation(value = "语言设置")
    public ResBody lang() {
        return ResBody.success();
    }


}
