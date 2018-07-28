package com.example.demo.controller

import com.example.demo.entity.Shift
import com.example.demo.entity.User
import com.example.demo.service.ShiftService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import utils.Message

@RestController
@RequestMapping("/shift")
class Shiftcontroller {

    @Autowired
    lateinit var shiftService: ShiftService
//    @Autowired
//    lateinit var shiftDaoService: ShiftDaoService

    @GetMapping("/show-shift")
    fun showShift(): Message<List<Shift>> {
        var message:Message<List<Shift>> = Message()
        val shifts:List<Shift> = shiftService.getShiftAll()

        message.data = shifts
        return  message
    }

    @GetMapping("/show-user")
    fun showUser(): Message<List<User>> {
        var message:Message<List<User>> = Message()

        val user = User("chen",1,12)

        val list = mutableListOf<User>()
        list.add(user)

        message.data = list
        return  message
    }


    @PostMapping("/")
    fun addShift(@RequestBody shifts: List<Shift>):String{
        print(shifts)
        return ""
    }
}
