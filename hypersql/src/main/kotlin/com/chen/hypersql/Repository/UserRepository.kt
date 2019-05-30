package com.chen.hypersql.Repository

import com.chen.hypersql.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository:JpaRepository<User,Int> {
}