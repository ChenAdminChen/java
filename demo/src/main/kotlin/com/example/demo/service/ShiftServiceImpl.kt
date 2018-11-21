package com.example.demo.service

import com.example.demo.dao.ShiftDaoService
import com.example.demo.entity.Shift
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ShiftServiceImpl : ShiftService {

    @Autowired
    lateinit var shiftDaoService: ShiftDaoService

    override fun getShiftAll(): List<Shift> {
        return shiftDaoService.getShiftAll()
    }
}