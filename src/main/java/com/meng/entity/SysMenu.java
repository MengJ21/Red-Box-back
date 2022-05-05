package com.meng.entity;

import java.util.List;

/**
 * @description: 菜单
 * @author 孟举
 * @date 2022/4/2 13:51
 * @version 1.0
 */

public class SysMenu {
    /*
     *
     *主键
     **/
    private long id;
    /*
     *
     *前端路由
     **/
    private String path;
    /*
     *
     *菜单图标
     **/
    private String icon;
    /*
     *
     *菜单标题
     **/
    private String title;
    /*
     *
     *前端组件
     **/
    private String component;
    /*
     *
     *子菜单。
     **/
    private List<SysMenu> children;
}
