package com.chen.ribbon.serviceribbon.controller;

import com.chen.ribbon.serviceribbon.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test/{name}")
    public String getTest(@PathVariable String name) {
        String result = testService.hiService(name);

        return result;

    }

}
