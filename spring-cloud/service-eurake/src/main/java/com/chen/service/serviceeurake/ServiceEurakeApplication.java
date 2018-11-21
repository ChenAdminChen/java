package com.chen.service.serviceeurake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceEurakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceEurakeApplication.class, args);
    }
}
