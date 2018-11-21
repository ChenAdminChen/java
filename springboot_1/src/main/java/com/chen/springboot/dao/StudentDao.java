package com.chen.springboot.dao;

import com.chen.springboot.Entity.Student;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class StudentDao {
    private static Map<Integer, Student> student;

    static{

        student = new HashMap<Integer, Student>()
        {
            {

                put(1, new Student(1, "chen", "computer course"));
                put(2, new Student(2, "chen 1", "computer course 1"));
                put(3, new Student(3, "chen 2", "computer course 2"));
            }
        };
    }


    public Collection<Student> getAllStudents() {
        return this.student.values();
    }

    public Student getStudentById(Integer id) {
        return this.student.get(id);
    }

    public Integer update(Student student) {
         this.student.put(student.getId(), student);
         return 1;
    }
}
