package com.chenbro.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SwaggerConfig
 * @Description TODO
 * @Author z8777
 * @Date 2021/3/13 10:49
 * @Version 1.0
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host("localhost:8088")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.chenbro.erp.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("勤诚条码系统")
                .description("勤诚条码-ERP接口文档")
                .version("1.0")
                .build();
    }
}
