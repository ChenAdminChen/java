package com.casoauth2.casoauth2zuul

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
class CasOauth2ZuulApplication

fun main(args: Array<String>) {
    runApplication<CasOauth2ZuulApplication>(*args)
}
