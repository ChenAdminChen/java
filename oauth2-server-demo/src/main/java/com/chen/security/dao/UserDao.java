package com.chen.security.dao;

import com.chen.security.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserDao
 * @DeScription TODO
 * @Author xiaobin
 * @Date 2019/3/1  8:54
 * @Version 1.0
 **/

@Mapper
@Repository
public interface UserDao {
    User selectByName(@Param("username") String username);
}
