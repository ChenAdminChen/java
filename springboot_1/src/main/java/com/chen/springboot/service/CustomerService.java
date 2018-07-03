package com.chen.springboot.service;

import com.chen.springboot.Entity.Customer;
import com.chen.springboot.dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findCustomer(){
       List<Customer> customerIterator = (List<Customer> )customerRepository.findAll();

       return customerIterator;

    }

}
