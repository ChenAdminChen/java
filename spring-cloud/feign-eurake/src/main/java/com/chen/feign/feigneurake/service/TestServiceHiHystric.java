package com.chen.feign.feigneurake.service;

import org.springframework.stereotype.Component;

@Component
public class TestServiceHiHystric implements TestService {
    @Override
    public String sayHiFromClientOne(String string) {
        return "sorry "+string;
    }
}
