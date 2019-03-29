package com.chen.clientc.repository

import com.chen.clientc.module.TeacherInfo
import org.omg.CORBA.Object
import org.springframework.data.jpa.repository.JpaRepository

interface TeacherInfoRepository:JpaRepository<TeacherInfo,Object> {
}