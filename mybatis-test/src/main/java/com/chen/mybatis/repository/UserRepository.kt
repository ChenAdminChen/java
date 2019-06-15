package com.chen.mybatis.repository

import com.chen.mybatis.entity.UserJpa
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserJpa, Int> {
}