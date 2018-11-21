package com.cas.oauth2.casoauth2server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CasOauth2ServerApplication

fun main(args: Array<String>) {
    runApplication<CasOauth2ServerApplication>(*args)
}
