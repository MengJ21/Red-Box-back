package com.meng.entity;

import lombok.Data;

import java.util.List;

/**
 * @description: 角色信息实体
 * @author 孟举
 * @date 2022/4/2 13:49
 * @version 1.0
 */
@Data
public class SysRole {
    /*
     *
     *主键
     **/
    private long id;
/*
 * 标签名称
 *
 **/
    private String label;
    /*
     *
     *标签值。
     **/
    private String code;
    /*
     *
     *角色对应的菜单列表。
     **/
    private List<SysMenu> menus;
    /*
     *
     *数据权限。
     **/
    private List<SysPermission> permissions;
}
