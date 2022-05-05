package com.meng.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户实体
 */
@Data
public class SysUser implements UserDetails {

    private long id;
    private String username;
    private String password;
    private Integer sex;
    private String avatar;
    private String address;
    private String openId;
    private boolean status;
    private boolean admin;
    private String phoneNumber;
    private List<SysRole> roles;
    /*
     * 权限数据
     *
     **/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_admin"));
//        roles.forEach(item ->{
//            list.add(new SimpleGrantedAuthority("ROLE_" + item.getCode()));
//        });
        return list;
    }

    /*
     *账号是否过期。
     **/
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }
    @Override
    public String getUsername(){
        return username;
    }
    /*
     *账号是否被锁定。
     **/

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
    /*
     *是否被禁用。
     **/
    @Override
    public boolean isEnabled() {
        return status;
    }
}
