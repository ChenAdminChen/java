package com.chen.redis.module

import java.io.Serializable

//@Entity(name = "teacher")
class Teacher:Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = 0

    var name: String? = null

    override fun toString(): String {
        return super.toString()
    }
}