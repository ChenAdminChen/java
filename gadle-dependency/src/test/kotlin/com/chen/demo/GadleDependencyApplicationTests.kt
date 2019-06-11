package com.chen.demo

import com.chen.demo.Controller.UserServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class GadleDependencyApplicationTests {

    var user: UserServer? = null
    @Before
    fun set() {
        user = UserServer()
    }

    @Test
    fun contextLoads() {
        user!!.getUser()
    }

}
