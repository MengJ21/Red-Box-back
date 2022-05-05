package com.meng.config.security.contents;
/**
 * @description: 白明单
 * @author 孟举
 * @date 2022/4/6 19:35
 * @version 1.0
 */

public class SecurityContents {
    public static final String[] WHITE_LIST={
            //后端登录接口
            "/user/login",

            //swagger相关。
            "/swagger-ui.html",
            "webjars/**",
            "swagger-resources/**",
            "v2/**",
            "configuration/ui",
            "configuration/security",


            "/doc.html",
            "/webjars/**",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/v2/api-docs-ext",
            "/configuration/ui",
            "/configuration/security",


    };
}
