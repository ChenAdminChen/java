package com.chen.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName User
 * @DeScription 用户类
 * @Author xiaobin
 * @Date 2019/3/1  8:51
 * @Version 1.0
 **/

public class User implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private String name;
    /**
     * @Description 账户是否过期,过期无法验证
     **/
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @Description 指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证
     **/
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @Description 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     **/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @Description 是否被禁用,禁用的用户不能身份验证
     **/
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * @Description 获得当前用户所拥有的权限
     **/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> ga = new ArrayList<SimpleGrantedAuthority>();
        ga.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return ga;
    }

    /**
     * @Description 获取密码
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * @Description 获取用户名
     */
    @Override
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
