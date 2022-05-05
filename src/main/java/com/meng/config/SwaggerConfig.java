package com.meng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: 接口文档配置类
 * @author 孟举
 * @date 2022/4/6 15:56
 * @version 1.0
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {
    /*
     *创建接口文档。
     **/
    @Bean
    public Docket createApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.meng.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }
    /*
     *文档信息。
     **/
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("RedBox")
                .version("1.0.0")
                .contact(new Contact("孟举","http://localhost:8081/doc.html","1747153981@qq.com"))
                .description("RedBox 接口文档")
                .build();
    }
    /*
     *设置请求信息
     **/
    private List<ApiKey> securitySchemes() {
        List<ApiKey> list = new ArrayList<>();
        ApiKey key = new ApiKey("Authorization", "Authorization", "Header");
        list.add(key);
        return list;
    }
    /*
     *配置swagger测试的权限
     **/
    public List<SecurityContext> securityContexts(){
        List<SecurityContext> list = new ArrayList<>();
        list.add(getSecurityContext());
        return list;
    }
    /*
     *得到授权路径。
     **/
    private SecurityContext getSecurityContext(){
        return SecurityContext
                .builder()
                .securityReferences(securityReferences())
                .forPaths(PathSelectors.regex("/hello/.*"))
                .build();
    }
    /*
     *给swagger进行接口测试
     **/
    private List<SecurityReference> securityReferences(){
        List<SecurityReference> list = new ArrayList<>();
        //授权范围
        AuthorizationScope scope = new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] scopes = new AuthorizationScope[1];
        scopes[0]=scope;
        list.add(new SecurityReference("Authorization",scopes));
        return list;
    }


}
