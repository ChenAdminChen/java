package com.chen.ribbon.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://service-hi/get-string?string=" + name, String.class);
    }

    //当没有找到service-hi 出错后的处理
    //控制了容器的线程阻塞
    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}
