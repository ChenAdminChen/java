package com.chen.clientA

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.support.AbstractApplicationContext

@SpringBootApplication
//@EnableDiscoveryClient

//@EnableResourceServer

@EnableFeignClients

@EnableDistributedTransaction

@EnableEurekaClient
class TmClientAApplication{
//
//    @Bean
//    fun oauth2RestTemplate(oAuth2ClientContext: OAuth2ClientContext, details: OAuth2ProtectedResourceDetails): OAuth2RestTemplate {
//        return OAuth2RestTemplate(details,oAuth2ClientContext)
//    }

//    fun  commandLineRunner(applicationContext: AbstractApplicationContext):CommandLineRunner{
//
//    }
}

fun main(args: Array<String>) {
    runApplication<TmClientAApplication>(*args)
}
