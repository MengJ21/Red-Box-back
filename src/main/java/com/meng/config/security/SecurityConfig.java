package com.meng.config.security;

import com.meng.config.security.contents.SecurityContents;
import com.meng.config.security.handler.JwtAccessDeniedHandler;
import com.meng.config.security.handler.JwtAuthenticationEntryPoint;
import com.meng.config.security.handler.JwtAuthenticationFilter;
import com.meng.config.security.service.UserDetailServiceImpl;
import com.meng.entity.SysUser;
import com.meng.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @description: 权限基本配置
 * @author 孟举
 * @date 2022/4/3 9:51
 * @version 1.0
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity//方法权限
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    /*
     *一般用来配置白名单
     * 白名单：没有权限也可以访问的资源
     **/

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers(SecurityContents.WHITE_LIST);
    }
    /*
     *security的核心配置
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //1.使用jwt，首先关闭跨域攻击
        http.csrf().disable();
        //2. 关闭session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //3.请求都需要认证之后才能访问，除白名单之外的资源。
        http.authorizeRequests()
                .anyRequest().authenticated();
        //4.禁用缓存
        http.headers().cacheControl();
        //5.token过滤器，校验token
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        //6.没有登录，没有权限访问资源，自定义返回结果。
        http.exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler);
    }

    /*
     *自定义登录逻辑的配置
     * 也即是配置到security中进行认证
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
