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
        assert(users.size == 4)
    }

    @Test
    fun delete() {
        var result = userMapper.deleteUser(3)

        assert(result > 0)

        assert(userMapper.getUserById(3) == null)

    }

    @Test
    fun deleteById() {


        var result = userMapper.deleteUser(8)

        assert(result == 0)

    }


    @Test
    fun update() {
        var u = User()
        u.email = "123456789@qq.com"
        u.id = 3
        var result = userMapper.updateUser(u)

        var users = userMapper.getUserById(3)

        assert(users != null)

        assert(users?.email.equals(u.email))

    }


    @Test
    fun add() {
        var u = User()
        u.email = "123456789@qq.com"
        u.password = "password"
        u.username = "fdfdf"

        var result = userMapper.addUser(u)

        assert(result > 0)

        assert(u.id != null)

        var users = userMapper.getUserById(u.id!!)

        assert(users != null)

        assert(users!!.disabled == false)

    }
}
