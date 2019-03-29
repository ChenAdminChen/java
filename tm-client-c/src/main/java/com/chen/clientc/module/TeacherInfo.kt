package com.chen.clientc.module

import javax.persistence.*

@Entity
@Table(name = "teacher_info")
//@Access(value= AccessType.FIELD)
class TeacherInfo {

    @Id
    @Column(name = "t_id")
    var tId: Int? = 0

    var info: String? = null

}