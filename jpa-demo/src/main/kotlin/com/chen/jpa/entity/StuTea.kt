package com.example.testkotlin.entity

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import javax.persistence.*

@Entity
@Table(name = "sch_tea")
class StuTea {

    var name: String? = null

    //GenericGenerator 将college 表中的主键作为该表的主键
    @EmbeddedId
//    @GenericGenerator(
//            name = "pkGenerator",
//            strategy = "foreign",
//            parameters = [Parameter(value ="college",name = "property"),Parameter(value ="grade",name = "property")]
//    )
    var stuTeaKey: StuTeaKey? = null

    //PrimaryKeyJoinColumn 主键作为关联字段
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "stuTea")
    @PrimaryKeyJoinColumn
    var college: College? = null

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "stuTea")
    @PrimaryKeyJoinColumn
    var grade: Grade? = null

}