package com.chen.clientA.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;

//,fallback = ClientBServiceFallback.class

@FeignClient(value = "tm-client-b",url = "${tm-client-a-url}")
public interface ClientBService {

    @RequestMapping(value = "/client-b-success", method = RequestMethod.GET, consumes = "application/json")
    String getClientBSuccess();

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json")
    String createClientB(@RequestParam("id") Integer id);

    @RequestMapping(value = "/client-b-throw-error", method = RequestMethod.POST, consumes = "application/json")
    String createClientBThrowError(@RequestParam("id") Integer id);


    @RequestMapping(value = "/client-b-transaction-error", method = RequestMethod.POST, consumes = "application/json")
    String createClientBTransactionError(@RequestParam("id") Integer id);


    @RequestMapping(value = "/client-b-transaction-error/{id}", method = RequestMethod.PUT, consumes = "application/json")
    String updateClientBTransactionError(@PathVariable("id") Integer id);

}
