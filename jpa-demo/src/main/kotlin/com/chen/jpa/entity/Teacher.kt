package com.example.testkotlin.entity

import javax.persistence.*

@Entity
@Table(name = "teacher")
class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = 0

    var name: String? = null

}