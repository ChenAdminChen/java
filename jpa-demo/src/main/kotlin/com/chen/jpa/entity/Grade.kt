package com.example.testkotlin.entity

import javax.persistence.*

@Entity
@Table(name = "grade")
class Grade {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var stuTeaKey: StuTeaKey? = null

    var grade: Int? = 0

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], optional = false)
    @PrimaryKeyJoinColumn
    private val stuTea: StuTea? = null
}