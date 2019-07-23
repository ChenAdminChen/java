package com.chen.demo.controller


import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RestController
class TestController {

    val log: Logger = LogManager.getLogger(TestController::class.java)

    @GetMapping("/hello")
    fun getHello(): ResponseEntity<String> {

        log.debug( RuntimeException("successs-----------------------").toString())
        return throw RuntimeException("successs-----------------------")
//        return ResponseEntity.ok("success");

    }
}