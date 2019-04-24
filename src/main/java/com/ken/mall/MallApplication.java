package com.ken.mall;

import com.ken.mall.dao.GenericRepositoryImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
@SpringBootApplication
@MapperScan("com.ken.mall.mapper")
@EnableJpaRepositories(repositoryBaseClass = GenericRepositoryImpl.class,
        basePackages = {"com.ken.mall.dao"},
        enableDefaultTransactions = false)
@EnableAspectJAutoProxy(exposeProxy = true)
public class MallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }

}
