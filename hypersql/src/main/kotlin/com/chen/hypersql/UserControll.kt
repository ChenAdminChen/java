package com.chen.hypersql

import com.chen.hypersql.Repository.UserRepository
import com.chen.hypersql.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserControll {

    @Autowired
    lateinit var userRepository: UserRepository

    @PostMapping("/")
    fun addUser(): ResponseEntity<String> {
        var user = User()
        user.name = "chen"

        userRepository.save(user);

        return ResponseEntity("success", HttpStatus.OK)
    }

    @GetMapping("/")
    fun getUser(): ResponseEntity<List<User>> {

        var users = userRepository.findAll()

        return ResponseEntity(users, HttpStatus.OK)
    }
}