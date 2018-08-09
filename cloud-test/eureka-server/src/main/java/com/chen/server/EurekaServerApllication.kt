package com.chen.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class EurekaServerApllication {
}

fun main(args: Array<String>) {
    runApplication<EurekaServerApllication>(*args)
}