package com.chen.mybatis.entity

import javax.persistence.*

@Entity
@Table(name = "user")
class UserJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    var password: String? = null
    var username: String? = null
    var email: String? = null

}