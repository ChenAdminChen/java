package com.example.demo.quartz.java

import org.quartz.DateBuilder.evenMinuteDate
import org.quartz.JobBuilder.newJob
import org.quartz.JobDetail
import org.quartz.Trigger
import org.quartz.TriggerBuilder.newTrigger
import org.quartz.impl.StdSchedulerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class SimpleExample {

//    @Autowired
//    private lateinit var scheduler: Scheduler

    fun runQuartz():String{
        var sf = StdSchedulerFactory()
        var sched = sf.scheduler
        var job: JobDetail? = newJob(HelloJob::class.java).withIdentity("job1", "group1").build()

        var runTime:Date = evenMinuteDate(Date())

        var trigger:Trigger = newTrigger().withIdentity("trigger1","trigger1").startAt(runTime).build()

        sched.scheduleJob(job,trigger)

        sched.start()

        Thread.sleep(90000)

        sched.shutdown()

        return ""
    }
}

fun main(args: Array<String>) {
    var simpleExample: SimpleExample = SimpleExample()
    simpleExample.runQuartz()
}