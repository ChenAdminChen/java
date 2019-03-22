package com.chen.clientA.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;

@FeignClient(value = "tm-client-b",url = "${tm-client-url}",fallback = ClientBServiceFallback.class)
public interface ClientBService {

    @RequestMapping(value = "/client-b-success", method = RequestMethod.GET, consumes = "application/json")
    String getClientBSuccess();

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json")
    String createClientB(@RequestParam("id") Integer id);

}
