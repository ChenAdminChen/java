package com.chen.demo.sprngjcahce.service

import com.chen.demo.sprngjcahce.entity.User
import org.springframework.stereotype.Service


interface UserService {
    fun getUser(): User
}