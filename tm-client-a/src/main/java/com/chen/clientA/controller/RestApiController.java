package com.chen.clientA.controller;

import com.chen.clientA.mapper.TeacherMapper;
import com.chen.clientA.module.Teacher;
import com.chen.clientA.services.ClientBService;
import com.chen.clientA.services.ClientCService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestApiController {

    @Autowired
    private ClientBService clientBService;

    @Autowired
    private ClientCService clientCService;
//    @Autowired
//    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherMapper teacherMapper;

    @GetMapping("/client-a-success")
    public String getClient() {
        return "success";
    }

    @GetMapping("/client-a-to-b")
    public String getClientBSuccess() {
        return null;
    }

    @PostMapping("/no-lcn")
    public String createTeacherNoLcn() {

        Teacher teacher = new Teacher();
        teacher.setName("client-a-success");

        teacherMapper.add(teacher);

        System.out.println("client a :" + teacher.getId());

        clientBService.createClientB(teacher.getId());


        throw new RuntimeException("test");

    }

    @PostMapping("/")
    @LcnTransaction
    @Transactional
    public String createTeacher() {

        Teacher teacher = new Teacher();
        teacher.setName("client-a-success");

        teacherMapper.add(teacher);

        String str = clientBService.createClientB(teacher.getId());

        return "client-b : " + str + " client success";
    }

    @PostMapping("/client-a-throw-exception")
    @LcnTransaction
    @Transactional
    public String createTeacherThrowException() {

        Teacher teacher = new Teacher();
        teacher.setName("client-a-success");

        teacherMapper.add(teacher);

        String str = null;
        try {
            str = clientBService.createClientB(teacher.getId());
        } catch (Exception e) {
            throw new RuntimeException("client b exception");
        }

        throw new RuntimeException("client a throw exception");
    }

    @PostMapping("/client-a-transaction-error")
    @LcnTransaction
    @Transactional
    public String createTeacherTransactionError() {

        Teacher teacher = new Teacher();
        teacher.setName("朝三暮四 朝秦暮楚国夺魂牵梦萦国朝三暮四fasldfkfeooefj霸业eofajdfal fda dfalfdkfafdslafdsafdksaf safsa fdsadfsafdaf");

        teacherMapper.add(teacher);

        String str = clientBService.createClientB(teacher.getId());

        return "client-b : " + str + " client success";
    }

    @PostMapping("/client-a-success-client-b-throw-error")
    @LcnTransaction
    @Transactional
    public String createTeacherClientASuccessClientBThrowError() {

        Teacher teacher = new Teacher();
        teacher.setName("client-a-success");

        teacherMapper.add(teacher);

        String str = clientBService.createClientBThrowError(teacher.getId());

        return "client-b : " + str + " client success";
    }

    @PostMapping("/client-a-success-client-b-transaction-error")
    @LcnTransaction
    @Transactional
    public String createTeacherClientASuccessClientBTransactionError() {

        Teacher teacher = new Teacher();
        teacher.setName("client-a-success");

        teacherMapper.add(teacher);

        String str = clientBService.createClientBTransactionError(teacher.getId());

        return "client-b : " + str + " client success";
    }

    @PostMapping("/client-a-error-client-b-success")
    @LcnTransaction
    @Transactional
    public String createTeacherClientAErrorClientBSuccess() {

        Teacher teacher = new Teacher();
        teacher.setName("client-a-success");

        teacherMapper.add(teacher);

        String str = clientBService.createClientB(teacher.getId());

        throw new RuntimeException("client-a-error and client-b-success");
    }

    @PutMapping("/client-a-success-client-b-error/{id}")
    @LcnTransaction
    @Transactional
    public String updateTeacherClientAErrorClientBSuccess(@PathVariable("id") Integer id) {

        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setName("update client-a");

        teacherMapper.udpate(teacher);

        String str = clientBService.updateClientBTransactionError(teacher.getId());

        throw new RuntimeException("client-a-error and client-b-success");
    }

    @PostMapping("/client-a-success-client-c-success")
    @LcnTransaction
    @Transactional
    public String createTeacherClientASuccessClientCSuccess() {

        Teacher teacher = new Teacher();
        teacher.setName("insert client-a");

        teacherMapper.add(teacher);

        String str = clientCService.createClientC(teacher.getId());

        return "client a success and " + str;

    }

    @PostMapping("/client-a-success-client-c-throw-error")
    @LcnTransaction
    @Transactional
    public String createTeacherClientASuccessClientCThrowError() {

        Teacher teacher = new Teacher();
        teacher.setName("insert client-a");

        teacherMapper.add(teacher);

        String str = clientCService.createClientCThrowError(teacher.getId());

        return "client a success and " + str;

    }
}
