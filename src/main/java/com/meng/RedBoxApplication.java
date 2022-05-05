package com.meng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.meng.mapper")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableSwagger2
public class RedBoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedBoxApplication.class, args);
    }

}
