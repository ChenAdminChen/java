package com.chen.mybatis

import com.chen.mybatis.entity.UserJpa
import com.chen.mybatis.repository.UserRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.junit4.SpringRunner
import javax.sql.DataSource


@RunWith(SpringRunner::class)
//@ExtendWith(SpringExtension::class)

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class JpaTestApplicationTests {

    @Autowired
    lateinit var userRepository: UserRepository


    @Test
    fun contextLoads() {

        var users = userRepository.findAll()

    }

    @Test
    fun delete() {
        userRepository.deleteById(3)
        var users = userRepository.findAll()

    }

    @Test
    fun update() {
        var u = UserJpa()
        u.email = "123456789@qq.com"
        u.id = 3

        userRepository.save(u)

        var users = userRepository.findById(3)
        print(users.toString())
    }

    @Test
    fun add(){
        var u = UserJpa()
        u.email = "123456789@qq.com"
        u.username ="ffdfd"
        u.password = "erer"

        userRepository.save(u)

        var user = userRepository.findById(u.id!!)
        print(user.toString())


    }
}
