package com.chen.redis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.redis.core.ValueOperations
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.support.AbstractApplicationContext
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.transaction.PlatformTransactionManager
import java.util.*


@SpringBootApplication
@EnableCaching
class SpringBootRedisDemoApplication {
    @Autowired
    private val template: StringRedisTemplate? = null

//    @Throws(Exception::class)
//    fun run(vararg args: String) {
//        val ops = this.template!!.opsForValue()
//        val key = "spring.boot.redis.test"
//        if (!this.template.hasKey(key)) {
//            ops.set(key, "foo")
//        }
//        println("Found key " + key + ", value=" + ops.get(key))
//    }

//    @Bean
//    fun commandLineRunner(applicationContext: AbstractApplicationContext): CommandLineRunner {
//        return args ->{
//
//        }
//    }
//

//    @Bean
//    fun init(ctx: ApplicationContext) = CommandLineRunner {
////        val beanNames = ctx.getBeanDefinitionNames()
////        Arrays.sort(beanNames)
////        for (beanName in beanNames) {
////            println(beanName)
////        }
//
//        val map = ctx.getBeansOfType(CacheManager::class.java)
//
//    }
}

fun main(args: Array<String>) {
    runApplication<SpringBootRedisDemoApplication>(*args)
}
