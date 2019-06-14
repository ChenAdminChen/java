package com.chen.mybatis

import com.chen.mybatis.entity.User
import com.chen.mybatis.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @Autowired
    lateinit var userMapper: UserMapper


    @GetMapping("/user")
    fun getUsers(): ResponseEntity<List<User>> {


        return ResponseEntity(userMapper.getUser(), HttpStatus.OK)
    }
}