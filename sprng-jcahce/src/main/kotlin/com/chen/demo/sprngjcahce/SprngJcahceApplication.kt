package com.chen.demo.sprngjcahce

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class SprngJcahceApplication

fun main(args: Array<String>) {
    runApplication<SprngJcahceApplication>(*args)
}
