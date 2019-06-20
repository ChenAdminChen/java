package com.chen.mybatis.mapper

import com.chen.mybatis.entity.User
import org.apache.ibatis.annotations.*

@Mapper
interface UserMapper {
    //     var id: Int? = null
//    var password: String? = null
//    var username: String? = null
//    var email: String? = null
    @Insert("insert into user(username,email,password) values(#{username},#{email},#{password})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    fun addUser(user: User): Int

    @Select("select * from  user")
    fun getUser(): List<User>

    @Select("select * from  user where id =#{id}")
    fun getUserById(id: Int): User

    @Delete("delete from user where id = #{id}")
    fun deleteUser(id: Int): Int

    @Update("update user set email = #{email} where id =#{id}")
    fun updateUser(user: User): Int
}