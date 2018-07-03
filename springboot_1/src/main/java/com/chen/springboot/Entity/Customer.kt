package com.chen.springboot.Entity

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.*

@Entity
class Customer (
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        var id:Int?=0,

        @Column(nullable = false)
        var firstName:String?="",

        @Column(nullable = false)
        var lastName:String?=""
) {

}