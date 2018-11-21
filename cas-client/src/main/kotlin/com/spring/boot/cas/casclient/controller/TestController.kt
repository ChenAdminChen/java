package com.spring.boot.cas.casclient.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping


@RestController
class TestController {
    @RequestMapping("/")
    fun index(): String {
        return "访问了首页哦"
    }


    @GetMapping("/hello")
    fun hello(): String {
        return "不验证哦"
    }

    @GetMapping("/auth")
    fun auth():Authentication{
        return SecurityContextHolder.getContext().authentication
    }

    @PreAuthorize("hasAuthority('TEST')")//有TEST权限的才能访问
    @GetMapping("/security")
    fun security(): String {
        return "hello world security"
    }

    @PreAuthorize("hasAuthority('ADMIN')")//必须要有ADMIN权限的才能访问
    @GetMapping("/authorize")
    fun authorize(): String {
        return "有权限访问"
    }


//    fun restful(){
//
//        val casUrlPrefix = "http://localhost:8080/cas"
//        val authenticator = CasRestAuthenticator(casUrlPrefix)
//        val client = CasRestFormClient(authenticator)
//
//// The request object must contain the CAS credentials
//        val webContext = J2EContext(request, response)
//        val profile = client.requestTicketGrantingTicket(context)
//        val casCreds = client.requestServiceTicket("<SERVICE_URL>", profile)
//        val casProfile = client.validateServiceTicket("<SERVICE_URL>", casCreds)
//        client.destroyTicketGrantingTicket(context, profile)
//    }
}