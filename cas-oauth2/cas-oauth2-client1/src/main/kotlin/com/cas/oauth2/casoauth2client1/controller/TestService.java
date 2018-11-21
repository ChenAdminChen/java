package com.cas.oauth2.casoauth2client1.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cas-oauth2-client2")
public interface TestService {

    @RequestMapping(value = "/get-a", method = RequestMethod.GET)
    String sayHiFromClientOne();

}
