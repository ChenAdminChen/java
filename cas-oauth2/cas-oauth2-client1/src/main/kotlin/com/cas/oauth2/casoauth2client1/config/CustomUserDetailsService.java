package com.cas.oauth2.casoauth2client1.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

//实现UserDetailsService接口，实现loadUserByUsername方法
//@Component
public class CustomUserDetailsService
        implements UserDetailsService {
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            System.out.println("当前的用户名是："+username);
            //这里我为了方便，就直接返回一个用户信息，实际当中这里修改为查询数据库或者调用服务什么的来获取用户信息
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername("admin");
            userInfo.setName("admin");
            Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();
            AuthorityInfo authorityInfo = new AuthorityInfo("TEST");
            authorities.add(authorityInfo);
            userInfo.setAuthorities(authorities);
            return userInfo;
        }


        //实现AuthenticationUserDetailsService，实现loadUserDetails方法
//     AuthenticationUserDetailsService<CasAssertionAuthenticationToken>
//	implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken>
//
//    {
//
//            @Override
//            public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
//                System.out.println("当前的用户名是："+token.getName());
//                /*这里我为了方便，就直接返回一个用户信息，实际当中这里修改为查询数据库或者调用服务什么的来获取用户信息*/
//                UserInfo userInfo = new UserInfo();
//                userInfo.setUsername("admin");
//                userInfo.setName("admin");
//                Set<AuthorityInfo> authorities = new HashSet<>();
//                AuthorityInfo authorityInfo = new AuthorityInfo("TEST");
//                authorities.add(authorityInfo);
//                userInfo.setAuthorities(authorities);
//                return userInfo;
//            }
}
