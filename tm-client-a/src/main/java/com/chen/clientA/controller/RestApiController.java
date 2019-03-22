package com.chen.clientA.controller;

import com.chen.clientA.module.Teacher;
import com.chen.clientA.repository.TeacherRepository;
import com.chen.clientA.services.ClientBService;
import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

    @Autowired
    private ClientBService clientBService;

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/client-a-success")
    public String getClient() {
        return "success";
    }

    @GetMapping("/client-a-to-b")
    public String getClientBSuccess(){
        return clientBService.getClientBSuccess();
    }

    @PostMapping("/")
    @TxcTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional
    public String createTest(){

        Teacher teacher = new Teacher();
        teacher.setName("test");

       Teacher teacher1 = teacherRepository.save(teacher);

        System.out.println("client a " + teacher1.getId());

        clientBService.createClientB(teacher1.getId());


        return "client a to client b success";
    }
}
