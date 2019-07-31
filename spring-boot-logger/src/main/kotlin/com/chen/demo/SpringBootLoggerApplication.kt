package com.chen.demo

import com.chen.demo.controller.TestController
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.lang.RuntimeException

@SpringBootApplication
class SpringBootLoggerApplication {

}

fun main(args: Array<String>) {
    val log: Logger = LogManager.getLogger(SpringBootLoggerApplication::class.java)

    log.debug(RuntimeException("successs-----------------------").toString())

    runApplication<SpringBootLoggerApplication>(*args)
}
