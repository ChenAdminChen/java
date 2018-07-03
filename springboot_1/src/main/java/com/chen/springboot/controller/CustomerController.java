package com.chen.springboot.controller;

import com.chen.springboot.Entity.Customer;
import com.chen.springboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/")
    public List<Customer> findAll(){
        return customerService.findCustomer();

    }
}
