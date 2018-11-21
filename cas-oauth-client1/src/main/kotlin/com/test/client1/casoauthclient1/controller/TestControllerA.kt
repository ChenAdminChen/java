package com.test.client1.casoauthclient1.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.client.OAuth2RestOperations
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@RestController
class TestControllerA {

    @Autowired
    private val oauth2RestTemplate: OAuth2RestOperations? = null

    @GetMapping("/test-a")
    fun add(): String {
        return "testControllerA"
    }

    @GetMapping("/test-a-find-b-auth")
    fun getOauth(): String {
        val result = oauth2RestTemplate!!.getForObject("http://127.0.0.1:8082/test-b", String::class.java)

        return result.toString()
    }


}