package com.cas.oauth2.casoauth2client2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

//@Configuration
public class AuthorizationSeverConfigurer extends ResourceServerConfigurerAdapter  {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        System.out.println("=============================================test");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
        System.out.println("==========================================test");
    }

}

