package com.chen.springboot.service;

import com.chen.springboot.Entity.Student;
import com.chen.springboot.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    public Collection<Student> getAllStudents(){
        return studentDao.getAllStudents();
    }

    public Student getStudentById(Integer id){
        return studentDao.getStudentById(id);
    }

    public Integer update(Student student) {
        return studentDao.update(student);
    }

}
