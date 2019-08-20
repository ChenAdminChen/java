package com.chen.demo.sprngjcahce.controller

import com.chen.demo.sprngjcahce.entity.User
import com.chen.demo.sprngjcahce.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.Mapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @Autowired
    lateinit var userService: UserService

    @GetMapping("/")
    fun getUser(): ResponseEntity<User> {
        val user = userService.getUser()
        return ResponseEntity.ok(user)
    }
}