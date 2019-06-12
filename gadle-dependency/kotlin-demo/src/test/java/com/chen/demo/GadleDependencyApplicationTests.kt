package com.chen.demo

import org.junit.Test

//@RunWith(SpringRunner::class)
//@SpringBootTest
class GadleDependencyApplicationTests {

    @Test
    fun contextLoads() {
        val javaUser = JavaUser()
        javaUser.id = 2

        assert(javaUser.id == 2)
        print("kotlin-test success")
    }

}
