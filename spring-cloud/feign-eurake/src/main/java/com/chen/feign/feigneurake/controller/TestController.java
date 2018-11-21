package com.chen.feign.feigneurake.controller;

import com.chen.feign.feigneurake.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/feign")
    public String getString(@RequestParam(name = "string") String string) {
        return testService.sayHiFromClientOne(string);
    }
}
