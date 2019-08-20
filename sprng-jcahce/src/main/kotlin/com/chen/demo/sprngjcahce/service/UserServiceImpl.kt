package com.chen.demo.sprngjcahce.service

import com.chen.demo.sprngjcahce.entity.User
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    @Cacheable(cacheNames = ["users"])
    override fun getUser(): User {
        var user = User("chen", 3, "chen-xin-yuan")

        println("------------create user in the userService -------")
        return user
    }


}