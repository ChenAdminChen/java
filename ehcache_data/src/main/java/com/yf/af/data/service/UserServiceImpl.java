package com.yf.af.data.service;

import com.yf.af.data.UserService;
import com.yf.af.data.mapper.UserMapper;
import com.yf.af.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chen on 2018/5/7.
 */
@Service("data.userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUser() {
        return userMapper.getUsers();
    }
}
