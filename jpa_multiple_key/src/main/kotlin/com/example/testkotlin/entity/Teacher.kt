package com.example.testkotlin.entity

import org.hibernate.annotations.CacheConcurrencyStrategy
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "teacher")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class Teacher : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = 0

    var name: String? = null

}