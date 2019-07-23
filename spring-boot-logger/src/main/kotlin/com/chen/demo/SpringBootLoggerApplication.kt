package com.chen.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootLoggerApplication

fun main(args: Array<String>) {
    runApplication<SpringBootLoggerApplication>(*args)
}
