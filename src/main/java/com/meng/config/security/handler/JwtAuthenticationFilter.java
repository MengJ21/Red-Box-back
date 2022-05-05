package com.meng.config.security.handler;

import com.meng.config.security.service.UserDetailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.meng.util.TokenUtil;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: token认证，在接口访问时进行过滤
 * @author 孟举
 * @date 2022/4/3 15:06
 * @version 1.0
 */

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private UserDetailServiceImpl userDetailsService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    /*
     *获取请求头信息token
     **/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1.获取token
        String header = request.getHeader(tokenHeader);
        log.info("获取token");
        //2.判断token是否存在
        if (null != header && header.startsWith(tokenHead)){
            log.info("token信息正确");
            //拿到token主体
            String token = header.substring(tokenHead.length() + 1);
            log.info("已经拿到token主体");
            //根据token获取用户名
            String username = tokenUtil.getUsernameByToken(token);
            //3.token存在，但是没有登录信息
            if (null != username && null == SecurityContextHolder.getContext().getAuthentication()){
                //没有登录信息，直接登录
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //判断token是否有效
                if (!tokenUtil.isExpiration(token) && username.equals(userDetails.getUsername())){
                    //刷新security中的用户信息
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        //过滤器放行 ?
        filterChain.doFilter(request,response);
    }
}
