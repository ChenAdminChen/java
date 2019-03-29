package com.chen.clientb

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.transaction.PlatformTransactionManager
import java.util.*

@SpringBootApplication
//@EnableResourceServer
@EnableFeignClients

@EnableEurekaClient

@EnableDistributedTransaction
class TmClientBApplication {

//    @Bean
//    fun oauth2RestTemplate(oAuth2ClientContext: OAuth2ClientContext, details: OAuth2ProtectedResourceDetails): OAuth2RestTemplate {
//        return OAuth2RestTemplate(details,oAuth2ClientContext)
//    }

//    @Bean
//    fun init(ctx: ApplicationContext) = CommandLineRunner {
//        val beanNames = ctx.getBeanDefinitionNames()
//        Arrays.sort(beanNames)
//        for (beanName in beanNames) {
//            println(beanName)
//        }
//
//        val map = ctx.getBeansOfType(PlatformTransactionManager::class.java)
//
//    }
}

fun main(args: Array<String>) {
    runApplication<TmClientBApplication>(*args)
}
