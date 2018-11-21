package com.spring.boot.cas.casclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CasClientApplication

fun main(args: Array<String>) {
    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier { _, _ ->
            true
    }

    runApplication<CasClientApplication>(*args)
}
