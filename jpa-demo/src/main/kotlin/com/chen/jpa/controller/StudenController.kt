package com.example.testkotlin.controller

import com.example.testkotlin.entity.College
import com.example.testkotlin.entity.StuTea
import com.example.testkotlin.repository.StuTeaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/student")
class StudenController {
    @Autowired
    lateinit var stuTeaRepository: StuTeaRepository


    @GetMapping("/student")
    fun getStudent(): ResponseEntity<List<StuTea>> {

        var stuTea = stuTeaRepository.findAll()
        var responseEntity = ResponseEntity(stuTea, HttpStatus.OK)


        return responseEntity
    }
}