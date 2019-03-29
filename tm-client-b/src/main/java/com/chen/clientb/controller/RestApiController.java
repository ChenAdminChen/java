package com.chen.clientb.controller;

import com.chen.clientb.mapper.TeacherInfoMapper;
import com.chen.clientb.module.TeacherInfo;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class RestApiController {

//    @Autowired
//    private TeacherInfoRepository teacherInfoRepository;

    @Autowired
    public TeacherInfoMapper teacherInfoMapper;

    @GetMapping("/client-b-success")
    public String getCleintB() {
        return "Success client b";
    }


    @PostMapping("/")
    @LcnTransaction
    @Transactional
    public String createTeacherInfo(@PathParam("id") Integer id) {
        TeacherInfo teacherInfo = new TeacherInfo();

        teacherInfo.setInfo("test success");

        teacherInfo.setTId(id);

        System.out.println(teacherInfo.toString());

        teacherInfoMapper.add(teacherInfo);

        return "insert into success client b";
    }

//    @PostMapping("/jpa")
//    @LcnTransaction
//    @Transactional
//    public String createTeacherInfoJpa(@PathParam("id") Integer id) {
//        TeacherInfoJpa teacherInfo = new TeacherInfoJpa();
//
//        teacherInfo.setInfo("test success");
//
//        teacherInfo.setTId(id);
//
//        teacherInfoRepository.save(teacherInfo);
//
//        return "insert into success client b";
//    }


    @PostMapping("/client-b-throw-error")
    @LcnTransaction
    @Transactional
    public String createTeacherInfoThrowError(@PathParam("id") Integer id) {
        TeacherInfo teacherInfo = new TeacherInfo();

        teacherInfo.setInfo("test client b  error");

        teacherInfo.setTId(id);

        System.out.println(teacherInfo.toString());

        teacherInfoMapper.add(teacherInfo);

        throw new RuntimeException("throw exception");
    }

    @PostMapping("/client-b-transaction-error")
    @LcnTransaction
    @Transactional
    public String createTeacherInfoTransactionError(@PathParam("id") Integer id) {
        TeacherInfo teacherInfo = new TeacherInfo();

        teacherInfo.setInfo("朝三暮四 朝秦暮楚国夺魂牵梦萦国朝三暮四fasldfkfeooefj霸业eofajdfal fda dfalfdkfafdslafdsafdksaf safsa fdsadfsafdaf");

        teacherInfo.setTId(id);

        teacherInfoMapper.add(teacherInfo);

        return "insert into success client b";
    }

    @PutMapping("/client-b-transaction-error/{id}")
    @LcnTransaction
    @Transactional
    public String updateTeacherInfoTransactionError(@PathVariable("id") Integer id) {
        TeacherInfo teacherInfo = new TeacherInfo();

        teacherInfo.setInfo("朝三暮四");

        teacherInfo.setTId(id);

        teacherInfoMapper.update(teacherInfo);

        return "update client b";
    }

    @PostMapping("/no-lcn")
    @LcnTransaction
    @Transactional
    public String createTeacherInfoNoLCN(@PathParam("id") Integer id) {
        TeacherInfo teacherInfo = new TeacherInfo();

        teacherInfo.setInfo("test success---");

        teacherInfo.setTId(id);

        System.out.println(teacherInfo.toString());

//        teacherInfoRepository.save(teacherInfo);

        teacherInfoMapper.add(teacherInfo);

        return "insert into success client b";
    }
}
