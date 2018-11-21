package com.example.demo.quartz

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.SchedulerFactoryBean

//@Configuration
class QuartzConfig {
 
    @Autowired
    private lateinit var schedulerFactoryBean: SchedulerFactoryBean

    @Bean
    fun schedulerFactoryBean():SchedulerFactoryBean{
        print("*****************************schedulerFactoryBean:"+schedulerFactoryBean)
        return schedulerFactoryBean
    }

}