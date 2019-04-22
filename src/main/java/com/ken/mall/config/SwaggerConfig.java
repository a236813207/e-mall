package com.ken.mall.config;

import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * com.anteng.quarantine.web.admin.config
 * author Daniel
 * 2018/1/31.
 */
@Configuration
@EnableSwagger2
@ConditionalOnClass(Docket.class)
public class SwaggerConfig {

    @Bean
    public Docket createUserRestApi() {
        ParameterBuilder builder = new ParameterBuilder();
        Parameter tokenParam = builder.name("x-access-token")
                .description("密钥")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(createApiInfo("电商前端接口",null,null))
                .groupName("auth-front-api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ken.mall.web.front"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(Lists.newArrayList(tokenParam));
    }

    @Bean
    public Docket createAdminRestApi() {
        ParameterBuilder builder = new ParameterBuilder();
        Parameter tokenParam = builder.name(HttpHeaders.AUTHORIZATION)
                .description("密钥")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(createApiInfo("管理后台接口",null,null))
                .groupName("auth-admin-api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ken.mall.web.admin"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(Lists.newArrayList(tokenParam));
    }

    private ApiInfo createApiInfo(String title, String description, String version) {
        return new ApiInfoBuilder()
                .title(title == null ? "电商接口" : title)
                .description(description == null ? "电商接口API" : description)
                .termsOfServiceUrl("")
                .contact(new Contact("Ken", "", ""))
                .version(version == null ? "1.0" : version)
                .build();
    }
}
