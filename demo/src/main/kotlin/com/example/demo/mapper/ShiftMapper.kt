package com.example.demo.mapper

import com.example.demo.entity.Shift
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

//
@Mapper
interface ShiftMapper {
    @Select("select * from shift")
    fun getShiftAll():List<Shift>
}