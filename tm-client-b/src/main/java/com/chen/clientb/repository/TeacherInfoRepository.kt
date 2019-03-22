package com.chen.clientb.repository

import com.chen.clientb.module.TeacherInfo
import org.springframework.data.jpa.repository.JpaRepository

interface TeacherInfoRepository:JpaRepository<TeacherInfo,Object> {
}