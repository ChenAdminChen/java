package com.chen.demo.hbasedemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HbaseDemoApplication

fun main(args: Array<String>) {
	runApplication<HbaseDemoApplication>(*args)
}
