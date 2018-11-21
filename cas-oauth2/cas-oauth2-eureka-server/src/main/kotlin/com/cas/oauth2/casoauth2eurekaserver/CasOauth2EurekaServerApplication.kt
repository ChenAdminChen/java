package com.cas.oauth2.casoauth2eurekaserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class CasOauth2EurekaServerApplication

fun main(args: Array<String>) {
    runApplication<CasOauth2EurekaServerApplication>(*args)
}
