package com.cas.oauth2.casoauth2client2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer

@SpringBootApplication
@EnableResourceServer
class CasOauth2Client2Application

fun main(args: Array<String>) {
    runApplication<CasOauth2Client2Application>(*args)
}
