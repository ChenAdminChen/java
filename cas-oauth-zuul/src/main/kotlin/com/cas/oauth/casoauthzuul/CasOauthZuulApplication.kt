package com.cas.oauth.casoauthzuul

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer

@SpringBootApplication
//@EnableZuulProxy
//@EnableOAuth2Sso
@EnableResourceServer
class CasOauthZuulApplication

fun main(args: Array<String>) {
    runApplication<CasOauthZuulApplication>(*args)
}
