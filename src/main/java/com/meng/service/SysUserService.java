package com.meng.service;

import com.meng.entity.SysUser;
import com.meng.vo.LoginVo;
import com.meng.util.Result;

/**
 * @description:
 * @author 孟举
 * @date 2022/4/2 12:49
 * @version 1.0
 */


public interface SysUserService {
    /*
     *登录接口
     * 登录参数有账号和密码。
     * 返回token用token取获取资源。
     **/
    Result login(LoginVo loginVo);
    /*
     *根据用户名获取用户信息。
     **/
    SysUser findByUsername(String username);
}
