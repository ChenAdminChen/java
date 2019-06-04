package com.example.testkotlin.entity

import org.hibernate.annotations.CacheConcurrencyStrategy
import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "college")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class College : Serializable {
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var stuTeaKey: StuTeaKey? = null

    var name: String? = null

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], optional = false)
    @PrimaryKeyJoinColumn
    private val stuTea: StuTea? = null
}