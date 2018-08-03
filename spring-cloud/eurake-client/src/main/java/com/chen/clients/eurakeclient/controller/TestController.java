package com.chen.clients.eurakeclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${server.port}")
    String port;

    @GetMapping("/get-string")
    public String getString(@RequestParam(name = "string") String string) {
        return string + "  port:" +port;
    }
}
