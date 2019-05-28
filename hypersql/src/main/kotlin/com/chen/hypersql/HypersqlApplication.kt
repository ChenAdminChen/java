package com.chen.hypersql

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HypersqlApplication

fun main(args: Array<String>) {
    runApplication<HypersqlApplication>(*args)
}
