package com.chen.security.Service;

import com.chen.security.dao.UserDao;
import com.chen.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @ClassName TUserService
 * @DeScription TODO
 * @Author yk
 * @Date 2019/4/1  17:35
 * @Version 1.0
 **/

@Service
public class TUserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //从数据库中获取
        User user = userDao.selectByName(s);
        //用户不能存在直接抛异常
        if(user==null){
            throw  new UsernameNotFoundException(s+"用户不存在");
        }
        return user;
    }
}
