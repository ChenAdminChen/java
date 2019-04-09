package com.chen.security

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
//@EnableAuthorizationServer
@MapperScan(basePackages = arrayOf("com.chen.security.dao"))
class Oauth2ServerDemoApplication


fun main(args: Array<String>) {
//    runApplication<Oauth2ServerDemoApplication>(args = *args)
    SpringApplication.run(Oauth2ServerDemoApplication::class.java, *args)
}

