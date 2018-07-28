package com.example.demo.dao

import com.example.demo.entity.Shift
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service


interface ShiftDaoService {

    fun getShiftAll():List<Shift>

}