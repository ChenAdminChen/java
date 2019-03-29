package com.chen.clientc

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients


@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableDistributedTransaction
class TmClientCApplication

fun main(args: Array<String>) {
    runApplication<TmClientCApplication>(*args)
}
