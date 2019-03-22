package com.chen.clientb

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableResourceServer
@EnableFeignClients

@EnableEurekaClient

@EnableDistributedTransaction
class TmClientBApplication {

//    @Bean
//    fun oauth2RestTemplate(oAuth2ClientContext: OAuth2ClientContext, details: OAuth2ProtectedResourceDetails): OAuth2RestTemplate {
//        return OAuth2RestTemplate(details,oAuth2ClientContext)
//    }

}

fun main(args: Array<String>) {
    runApplication<TmClientBApplication>(*args)
}
