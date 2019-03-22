package com.chen.clientb.module

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "teacher_info")
class TeacherInfo {

    @Column(name = "t_id")
    @Id
    var tId: Int? = 0

    var info: String? = null
}