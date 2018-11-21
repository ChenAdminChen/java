package com.chen.feign.feigneurake.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-hi",fallback = TestServiceHiHystric.class)
public interface TestService {

    @RequestMapping(value = "/get-string", method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "string") String string);

}
