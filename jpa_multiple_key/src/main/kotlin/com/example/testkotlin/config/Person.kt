package com.example.testkotlin.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import javax.validation.constraints.Email

@Configuration
@ConfigurationProperties(prefix = "person")
data class Person( var name: String? = "",

                  var id: Int? = 0) {
    //@Email 在赋值时进行检查
//    @Email


    override fun toString(): String {
        return "Person(name=$name, id=$id)"
    }


}