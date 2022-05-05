package com.meng.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry
                //允许访问的路径
                .addMapping("/**")
                //允许请求来源
                .allowedOrigins("http://localhost:8080")
                //允许跨域的方法
                .allowedMethods("GET","POST","DELETE","PUT","OPTION")
                //允许存在请求头
                .allowCredentials(true)
                //定义一个请求头携带token来访问我们的资源
                //.allowedHeaders()
                //最大效应时间
                .maxAge(3600);

    }

}
