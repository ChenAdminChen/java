package com.spring.boot.cas.casclient.module;

import com.spring.boot.cas.casclient.config.AuthorityInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * definition user entity
 *
 *  实例化对象时一定要为true,并且 authorities不能为null但可为空
 * @isAccountNonExpired true
 * @isAccountNonLocked true
 * @isCredentialsNonExpired true
 * @isEnabled true
 *
 *
 */
public class UserInfo implements UserDetails {
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 登录名称
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    private  boolean isAccountNonExpired = true;

    private  boolean isAccountNonLocked = true;

    private  boolean isCredentialsNonExpired = true;

    private  boolean isEnabled = true;

    private Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Set<AuthorityInfo> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public UserInfo() {
    }

}
