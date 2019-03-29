package com.chen.clientc.repository

import com.chen.clientc.module.Teacher
import org.springframework.data.jpa.repository.JpaRepository


interface TeacherRepository :JpaRepository<Teacher,Int>{
}