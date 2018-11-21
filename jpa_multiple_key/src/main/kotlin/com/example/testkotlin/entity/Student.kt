package com.example.testkotlin.entity

import org.apache.tomcat.jni.Address
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import javax.persistence.*
import javax.persistence.JoinColumn


@Entity
@Table(name = "student")
class Student : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = 0

    var name: String? = null

}