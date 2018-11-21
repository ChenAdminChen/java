package com.cas.oauth2.casoauth2client2.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class TestController {

//    @PreAuthorize("hasAuthority('TEST-2')")
    @GetMapping("/get-a")
    fun getA(principal: Principal):String{
        return "client 2 test"
    }

    @GetMapping("/get-b")
    fun getB():String{
        return "client 2"
    }
}