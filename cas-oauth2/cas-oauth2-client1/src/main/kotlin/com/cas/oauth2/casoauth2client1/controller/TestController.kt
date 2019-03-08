package com.cas.oauth2.casoauth2client1.controller

import com.cas.oauth2.casoauth2client1.feign.OAuth2FeignRequestInterceptor
import com.cas.oauth2.permission.anno.PermissionDependency
import feign.RequestInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.oauth2.client.OAuth2RestOperations
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


@RestController
class TestController {

    @Autowired
    private val oauth2RestTemplate: OAuth2RestOperations? = null

    @Autowired
    private val testService: TestService? = null;

    private val requestTokenBearerInterceptor : RequestInterceptor?=null


    @PermissionDependency(value = "test")
    @PreAuthorize("hasAuthority('TEST')")//有TEST权限的才能访问
    @GetMapping("/get-a")
    fun getA(): String {
        return "client 1"
    }

    @GetMapping("/get-b")
    fun getB(): String {
        return "client 1"
    }

    @GetMapping("/get-client2")
    fun getOauth(p:Principal): String {

        val result = oauth2RestTemplate!!.getForObject("http://127.0.0.1:9999/api-b/get-a", String::class.java)

        return result.toString() + " test"
    }

    @GetMapping("/get-feign-oauth")
    fun getOauthFeign(): String {

        val result = testService!!.sayHiFromClientOne()

        return result.toString() + " oauth feign"
    }
}