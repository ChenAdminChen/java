package com.example.testkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class TestKotlinApplication

fun main(args: Array<String>) {
    runApplication<TestKotlinApplication>(*args)
}
