package com.example.testkotlin.repository

import com.example.testkotlin.entity.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.webmvc.spi.BackendIdConverter
import org.springframework.stereotype.Component
import java.io.Serializable

interface StuTeaRepository : JpaRepository<StuTea, StuTeaKey> {

}

interface CollegeRepository : JpaRepository<College, StuTeaKey> {
}


interface GradeRepository : JpaRepository<Grade, StuTeaKey> {
}

interface StudentRepository : JpaRepository<Student, Int> {
}

interface TeacherRepository : JpaRepository<Teacher, Int> {

}

@Component
class StuTeaKeyCoverter : BackendIdConverter {
    override fun toRequestId(p0: Serializable?, p1: Class<*>?): String {
        p0 ?: return ""

        val parts = p0 as StuTeaKey
        return "${parts.studentId}_${parts.teacherId}"
    }

    override fun fromRequestId(p0: String?, p1: Class<*>?): Serializable? {
        p0 ?: return null

        val p = p0.split("_")

        return StuTeaKey(p[1].toInt(), p[0].toInt())
    }

    override fun supports(p0: Class<*>?): Boolean {
        return College::class.java == p0 || Grade::class.java == p0 || StuTea::class.java == p0
    }

}