package com.cas.oauth2.casoauth2client1.config;

import com.cas.oauth2.casoauth2client1.controller.TestService;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;

@Configuration
public class CustomUserInfoTokenServices {

    @Autowired
    private TestService testService;

    /**
     * package org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerTokenServicesConfiguration
     * 类中存在userInfoTokenServices()方法，其中变量authoritiesExtractor(AuthoritiesExtractor)不为空时使用自定义的权限,
     * 因此自定义下面的Bean使得authoritiesExtractor变量值不为空
     *
     * @return
     */
    @Bean
    public AuthoritiesExtractor authoritiesExtractor() {
        return map -> {

            try {
                String result = testService.sayHiFromClientOne();
                System.out.println(result + ":---------------- result");
            } catch (Exception e) {
                e.printStackTrace();
            }

            return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN");

        };
    }

}
