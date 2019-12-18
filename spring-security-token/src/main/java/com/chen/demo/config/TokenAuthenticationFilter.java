package com.chen.demo.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TokenAuthenticationFilter extends GenericFilterBean {

    //仅用于测试
    Map<String, User> users = new HashMap<>();
    TokenAuthenticationFilter() {

        List<SimpleGrantedAuthority> list = new ArrayList();
        list.add(new SimpleGrantedAuthority("read"));

        //Populate SecurityContextHolder by fetching relevant information using token
        final User user = new User(
                "chen",
                "1111111",
                true,
                true,
                true,
                true,
                list);

        users.put("c443bf6a-43b4-49b9-bcff-df732b1a9dcb", user);
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;

        //get token from Authorization
        final String authorization = httpRequest.getHeader("Authorization");

        String[] authorizations = authorization.split(" ");
        if (authorizations.length == 2 && authorizations[0].equals("Bearer")) {

            //check token
            User user = getUser(authorizations[1]);

            if (user != null) {

                final UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

        }

        chain.doFilter(request, response);
    }

    //获得用户信息,同时 user中带有权限信息
    private User getUser(String token) {

        return users.get(token);
    }

}