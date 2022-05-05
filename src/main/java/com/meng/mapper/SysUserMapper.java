package com.meng.mapper;

import com.meng.entity.SysUser;

import java.util.List;

/**
 * 用户有相关的操作。
 */

public interface SysUserMapper {
    /**
     * 查询所有用户信息。
     * @return
     */
    List<SysUser> findAll();

    SysUser findByUsername(String username);
}
