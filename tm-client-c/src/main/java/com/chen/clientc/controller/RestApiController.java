package com.chen.clientc.controller;


import com.chen.clientc.module.Teacher;
import com.chen.clientc.module.TeacherInfo;
import com.chen.clientc.repository.TeacherInfoRepository;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class RestApiController {

    @Autowired
    private TeacherInfoRepository teacherInfoRepository;

    @GetMapping("/client-a-success")
    public String getClient() {
        return "success";
    }

    @PostMapping("/")
    @LcnTransaction
    @Transactional
    public String createTeacherInfo(@PathParam("id") Integer id) {

        TeacherInfo teacherInfo = new TeacherInfo();
        teacherInfo.setInfo("test");

        teacherInfo.setTId(id);

        teacherInfoRepository.save(teacherInfo);

        return "client b success";
    }

    @PostMapping("/client-c-throw-error")
    @LcnTransaction
    @Transactional
    public String createTeacherInfoThrowError(@PathParam("id") Integer id) {

        TeacherInfo teacherInfo = new TeacherInfo();
        teacherInfo.setInfo("test");

        teacherInfo.setTId(id);

        teacherInfoRepository.save(teacherInfo);

        throw new RuntimeException("test");
    }
}
