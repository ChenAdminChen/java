package com.example.demo.entity

/**
 * Created by Hzy on 2018/7/24
 */
data class ShiftStat (
    var id: Int,
    var indexId: Int,
    var data: Float,
    var time: String,
    var shift: Int,
    var name: String?=null,
    var unit: String?=null
)

//    constructor(id: Int?, indexId: Int?, data: Float?, time: String?, shift: Int?) {
//        this.id = id
//        this.indexId = indexId
//        this.data = data
//        this.time = time
//        this.shift = shift
//    }
//
//    constructor(id: Int?, indexId: Int?, data: Float?, time: String?, shift: Int?, name: String?, unit: String?) {
//        this.id = id
//        this.indexId = indexId
//        this.data = data
//        this.time = time
//        this.shift = shift
//        this.name = name
//        this.unit = unit
//    }
//
//    constructor()

//    override fun toString(): String {
//        return "ShiftStat(id=$id, indexId=$indexId, data=$data, time=$time, shift=$shift, name=$name, unit=$unit)"
//    }
//
//}