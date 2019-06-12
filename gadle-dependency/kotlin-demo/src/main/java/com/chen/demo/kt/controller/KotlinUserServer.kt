package com.chen.demo.kt.controller

import com.chen.demo.JavaUser
import com.chen.demo.KotlinUser
import com.chen.demo.User
import org.springframework.stereotype.Service
import kotlin.collections.MutableList as MutableList1

@Service
open class KotlinUserServer {

    fun getJavaUserAndKotlinUser(): List<User> {
        val javaUser = JavaUser()
        javaUser.id = 2
        javaUser.name = "java-demo"
        javaUser.password = "xxxx"

        var kotlinUser = KotlinUser(1, "kotlin-demo", "xxx")

        var list = mutableListOf<User>()

        list.add(javaUser)
        list.add(kotlinUser)
        return list

    }
}