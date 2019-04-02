package com.chen.redis.controller;

import com.chen.redis.mapper.TeacherMapper;
import com.chen.redis.module.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestApiController {

    @Autowired
    private TeacherMapper teacherMapper;

    @GetMapping("/")
    public ResponseEntity<List<Teacher>> getTeachers() {
        List<Teacher> list = teacherMapper.select();
        ResponseEntity<List<Teacher>> responseEntity = new ResponseEntity<>(list, HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeachers(@PathVariable("id")Integer id) {
        Teacher list = teacherMapper.selectById(id);
        ResponseEntity<Teacher> responseEntity = new ResponseEntity<>(list, HttpStatus.OK);

        return responseEntity;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateTeacher(@PathVariable("id") Integer id, @RequestBody Teacher teacher){

        teacher.setId(id);
        teacherMapper.update(teacher);

        ResponseEntity<Integer> responseEntity = new ResponseEntity("成功",HttpStatus.OK);

        return responseEntity;
    }

    @PutMapping("/{id}/not-all-entries")
    public ResponseEntity<Integer> updateTeacherNotAllEntries(@PathVariable("id") Integer id, @RequestBody Teacher teacher){

        teacher.setId(id);
        teacherMapper.updateNotAllEntries(teacher);

        ResponseEntity<Integer> responseEntity = new ResponseEntity("成功",HttpStatus.OK);


        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteTeacher(@PathVariable("id") Integer id){

        teacherMapper.delete(id);

        ResponseEntity<Integer> responseEntity = new ResponseEntity("delete ",HttpStatus.OK);


        return responseEntity;
    }
}
