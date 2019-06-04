package com.example.testkotlin.entity

import java.time.Instant

class User {
    var token: String? = null
    var expired: Instant = Instant.now()
    var name: String? = null
}