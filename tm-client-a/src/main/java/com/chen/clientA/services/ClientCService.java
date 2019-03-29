package com.chen.clientA.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//,fallback = ClientBServiceFallback.class
@FeignClient(value = "tm-client-c", url = "${tm-client-c-url}")
public interface ClientCService {

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json")
    String createClientC(@RequestParam("id") Integer id);

    @RequestMapping(value = "/client-c-throw-error", method = RequestMethod.POST, consumes = "application/json")
    String createClientCThrowError(@RequestParam("id") Integer id);

}
