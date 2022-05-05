package com.meng.service.impl;

import com.meng.config.security.service.UserDetailServiceImpl;
import com.meng.entity.SysUser;
import com.meng.mapper.SysUserMapper;
import com.meng.service.SysUserService;
import com.meng.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.meng.util.Result;
import com.meng.util.TokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 
 * @author 孟举
 * @date 2022/4/2 12:53
 * @version 1.0
 */
@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private UserDetailServiceImpl userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenUtil tokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    /*
     *实现登录接口。
     **/
    @Override
    public Result login(LoginVo loginVo) {
        log.info("1.开始登录");
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginVo.getUsername());
        log.info("2.判断用户名密码是否正确。");
        if (null == userDetails || !passwordEncoder.matches(loginVo.getPassword(),userDetails.getPassword())){
            return Result.fail("账号或密码错误，请重新输入！");

        }
        if (!userDetails.isEnabled()){
            return Result.fail("该账户已经禁用，请联系管理员!");
        }
        log.info("登录成功，在security对象中存入登录者信息。");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        log.info("根据登录信息获取token");
        //需要借助jwt来生成token
        String token = tokenUtil.generateToken(userDetails);
        Map<String, String> map = new HashMap<>(2);
        map.put("tokenHead", tokenHead);
        map.put("token",token);
        return Result.success("登录成功",map);
    }
    /*
     *根据用户名获取用户对象。
     **/
    @Override
    public SysUser findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
