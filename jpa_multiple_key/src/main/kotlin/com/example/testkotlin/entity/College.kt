package com.example.testkotlin.entity

import javax.persistence.*


@Entity
@Table(name = "college")
class College {
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var stuTeaKey: StuTeaKey? = null

    var name: String? = null

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], optional = false)
    @PrimaryKeyJoinColumn
    private val stuTea: StuTea? = null
}