package com.chen.hypersql.entity

import javax.persistence.*

@Entity
@Table(name = "user")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = 0

    var name: String? = null

}
