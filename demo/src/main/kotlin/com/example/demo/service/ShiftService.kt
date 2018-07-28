package com.example.demo.service

import com.example.demo.entity.Shift
import org.springframework.stereotype.Service


interface ShiftService {

    fun getShiftAll():List<Shift>

}