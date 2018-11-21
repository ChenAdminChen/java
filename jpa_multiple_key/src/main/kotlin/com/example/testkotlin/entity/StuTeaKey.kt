package com.example.testkotlin.entity

import org.jetbrains.annotations.NotNull


import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class StuTeaKey(

//    @NotNull
    @Column(name = "teacher_id")
    val teacherId: Int = 0,

//    @NotNull
    @Column(name = "student_id")
    val studentId: Int = 0
    ) : Serializable
