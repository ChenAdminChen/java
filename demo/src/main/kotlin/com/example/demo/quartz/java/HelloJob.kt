package com.example.demo.quartz.java

import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.stereotype.Component

//@Component
class HelloJob : Job {

    override fun execute(context: JobExecutionContext?) {
        println("============hello word===========")
    }
}