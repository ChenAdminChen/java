package com.cas.oauth2.casoauth2client1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.xml.ws.soap.Addressing;

@Configuration
public class AuthorizationSeverConfigurer extends ResourceServerConfigurerAdapter  {

    @Autowired
    private  CustomUserDetailsService customUserDetailsService;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.authenticationManager(authenticationManagerServer);
        System.out.println("=============================================test");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { // 7
        auth
                .userDetailsService(customUserDetailsService);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
        System.out.println("==========================================test");
    }


}

