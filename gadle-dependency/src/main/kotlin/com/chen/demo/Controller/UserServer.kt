package com.chen.demo.Controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServer {

    @Autowired
    lateinit var user: KotlinUserServer

    fun getUser() {
        var list = user.getJavaUserAndKotlinUser()

        list.iterator().forEach { r -> print(r.toString()) }
    }
}