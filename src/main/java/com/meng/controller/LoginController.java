package com.meng.controller;

import com.meng.service.SysUserService;
import com.meng.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.meng.util.Result;

/**
 * 登录
 * 退出
 * 获取当前用户基本信息相关的接口
 */
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private SysUserService userService;

    @PostMapping("/login")
    public Result logIn(@RequestBody LoginVo loginVo){

        return userService.login(loginVo);
    }
}
