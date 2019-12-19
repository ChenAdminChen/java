package com.chen.clientc.module

import javax.persistence.*

@Entity
@Table(name = "teacher")
class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = 0

    var name: String? = null

}