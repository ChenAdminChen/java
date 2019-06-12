package com.chen.demo

import com.chen.demo.controller.UserServer
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class GadleDependencyApplicationTests {

    @Autowired
    var user: UserServer? = null
//    @Before
//    fun set() {
//        user = UserServer()
//    }

    @Test
    fun contextLoads() {
        var list = user!!.getUser()


        println("----------------------gradle-dependency print : ${list.size}----------")

        assert(list.size == 2)
    }

}
