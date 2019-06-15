package com.chen.mybatis.entity

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "user")
class UserJpa {
    var id: Int? = null
    var password: String? = null
    var username: String? = null
    var email: String? = null
}