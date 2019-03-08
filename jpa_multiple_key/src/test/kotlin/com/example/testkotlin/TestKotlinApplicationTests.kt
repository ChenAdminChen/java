package com.example.testkotlin

import com.example.testkotlin.config.Person
import com.example.testkotlin.config.Person1
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class TestKotlinApplicationTests {

    @Autowired
    lateinit var person: Person

    @Autowired
    lateinit var person1: Person1

    @Test
    fun contextLoads() {
        print(person.toString())

        print(person1.toString())
    }
}
