package com.chen.demo.controller

import com.chen.demo.User
import com.chen.demo.kt.controller.KotlinUserServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServer {

    @Autowired
    lateinit var user: KotlinUserServer

    fun getUser(): List<User> {
        var list = user.getJavaUserAndKotlinUser()

        list.iterator().forEach { r -> println(r.toString()) }

        return list
//        return listOf()
    }
}