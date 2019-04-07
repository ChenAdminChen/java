package com.chen.redis.module

import java.io.Serializable

//@Entity(name = "teacher")
class Teacher:Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Integer? = null

    var name: String? = null

    override fun toString(): String {
        return super.toString()
    }
}