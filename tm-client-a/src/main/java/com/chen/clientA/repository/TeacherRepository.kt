package com.chen.clientA.repository

import com.chen.clientA.module.Teacher
import org.springframework.data.jpa.repository.JpaRepository

interface TeacherRepository :JpaRepository<Teacher,Int>{
}