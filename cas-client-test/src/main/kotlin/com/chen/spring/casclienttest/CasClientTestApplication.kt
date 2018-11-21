package com.chen.spring.casclienttest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class CasClientTestApplication

fun main(args: Array<String>) {
    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier { _, _ ->
        true
    }

    @Bean
    fun  bCryptPasswordEncoder():BCryptPasswordEncoder {
        return  BCryptPasswordEncoder()
    }

    runApplication<CasClientTestApplication>(*args)
}
