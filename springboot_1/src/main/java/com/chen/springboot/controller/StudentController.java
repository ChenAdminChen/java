package com.chen.springboot.controller;

import com.chen.springboot.Entity.Student;
import com.chen.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/")
    public Collection<Student> getStudents(){
        Collection<Student> students = studentService.getAllStudents();
        return students;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student getStudentById(@PathVariable("id" )Integer id){
        Student student = studentService.getStudentById(id);
        return student;
    }

    @RequestMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public Integer updatStudentById(@PathVariable("id")Integer id, @RequestBody Student student){
        studentService.update(student);

        return 1;
    }
}
