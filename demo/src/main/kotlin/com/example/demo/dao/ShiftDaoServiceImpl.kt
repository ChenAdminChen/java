package com.example.demo.dao

import com.example.demo.entity.Shift
import com.example.demo.mapper.ShiftMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ShiftDaoServiceImpl :ShiftDaoService {

    @Autowired
    lateinit var shiftMapper: ShiftMapper

    override fun getShiftAll(): List<Shift> {
        return shiftMapper.getShiftAll()
    }
}