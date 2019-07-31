package com.chen.demo.config

import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
@Order(1)
class RunApplication {

    @PostConstruct
    fun test(){
        print("project start ,run this function======================")
    }
}