package com.cas.oauth2.casoauth2client1.controller;

import org.springframework.stereotype.Component;

@Component
public class TestServiceHiHystric implements TestService {
//    @Override
//    public String sayHiFromClientOne(String string) {
//        return "sorry "+string;
//    }

    @Override
    public String sayHiFromClientOne() {
        return "sorry ";
    }
}
