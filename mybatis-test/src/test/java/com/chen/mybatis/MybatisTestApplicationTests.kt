package com.chen.mybatis

import com.chen.mybatis.entity.User
import com.chen.mybatis.mapper.UserMapper
import org.junit.Test
import org.junit.runner.RunWith
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
//@SpringBootTest
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MybatisTestApplicationTests {

    @Autowired
    lateinit var userMapper: UserMapper

    @Test
    fun contextLoads() {

        var users = userMapper.getUser()

    }

    @Test
    fun delete() {
        userMapper.deleteUser(3)
        var users = userMapper.getUser()

    }

    @Test
    fun update() {
        var u = User()
        u.email = "123456789@qq.com"
        u.id = 3
        userMapper.updateUser(u)

        var users = userMapper.getUserById(3)
        print(users.toString())
    }


    @Test
    fun add() {
        var u = User()
        u.email = "123456789@qq.com"
        u.password = "password"
        u.username = "fdfdf"
        userMapper.addUser(u)

        userMapper.addUser(u)
        var users = userMapper.getUserById(u.id!!)

        print(users.toString())
    }
}
