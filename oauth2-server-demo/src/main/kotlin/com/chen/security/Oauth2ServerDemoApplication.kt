package com.chen.security

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Oauth2ServerDemoApplication

fun main(args: Array<String>) {
    runApplication<Oauth2ServerDemoApplication>(*args)
}
