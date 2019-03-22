package com.chen.clientb.controller;

import com.chen.clientb.module.TeacherInfo;
import com.chen.clientb.repository.TeacherInfoRepository;
import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

@RestController
public class RestApiController {

    @Autowired
    private TeacherInfoRepository teacherInfoRepository;

    @GetMapping("/client-b-success")
    public String getCleintB() {
        return "Success client b";
    }

    @PostMapping("/")
    @TxcTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional
    public String createTeacherInfo(@PathParam("id") Integer id) {
        TeacherInfo teacherInfo = new TeacherInfo();

        teacherInfo.setInfo("test success");

        teacherInfo.setTId(id);

        System.out.println(teacherInfo.toString());

        teacherInfoRepository.save(teacherInfo);

//        return "insert into success client b";
        throw new RuntimeException("tt");
    }

    @PostMapping("/no-lcn")
    @TxcTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional
    public String createTeacherInfoNoLCN(@PathParam("id") Integer id) {
        TeacherInfo teacherInfo = new TeacherInfo();

        teacherInfo.setInfo("test success");

        teacherInfo.setTId(id);

        System.out.println(teacherInfo.toString());

        teacherInfoRepository.save(teacherInfo);

        throw new RuntimeException("tt");
//        return "insert into success client b";
    }
}
