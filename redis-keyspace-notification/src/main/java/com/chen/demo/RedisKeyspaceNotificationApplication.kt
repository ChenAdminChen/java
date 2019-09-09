package com.chen.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RedisKeyspaceNotificationApplication

fun main(args: Array<String>) {
    runApplication<RedisKeyspaceNotificationApplication>(*args)
}
