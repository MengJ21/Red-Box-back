package com.meng.config.security.service;

import com.meng.entity.SysUser;
import com.meng.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 * @description: 实现UserDetailsService接口,实现自定义登录逻辑
 * 重写loadUserByUsername方法
 * @author 孟举
 * @date 2022/4/4 20:06
 * @version 1.0
 */

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //在mapper中自定义登录，根据用户名获取用户信息。
        SysUser user = userMapper.findByUsername(username);
        if (user == null){
            throw new RuntimeException("用户名或密码错误");
        }
        return user;
    }
}
