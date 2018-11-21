package com.cas.oauth.casoauthclient2.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class TestControllerB {

    @GetMapping("/test-b")
    fun add(user: Principal):String{

        return "testControllerB"
    }
}