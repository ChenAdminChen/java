package com.chen.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CamelDemoApplication

fun main(args: Array<String>) {
    runApplication<CamelDemoApplication>(*args)
}
