package com.example.testkotlin.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource(value = ["classpath:person1.properties"])
@ConfigurationProperties(prefix = "person")
data class Person1(var name: String? = "",

                   var id: Int? = 0) {
    //@Email 在赋值时进行检查
//    @Email


    override fun toString(): String {
        return "Person1(name=$name, id=$id)"
    }
}