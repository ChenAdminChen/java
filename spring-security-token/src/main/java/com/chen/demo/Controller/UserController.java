package com.chen.demo.Controller;

import com.chen.demo.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user-error")
    @PreAuthorize("hasAuthority('readss')")
    public User getUserError() {
        User user = new User();
        user.setName("error User");
        user.setGender(new Byte("1"));
        user.setEmail("123456@qq.com");

        return user;
    }

    @GetMapping("/user-authorize")
    @PreAuthorize("hasAuthority('read')")
    public User getUserAuthorize() {
        User user = new User();
        user.setName("user-authorize");
        user.setGender(new Byte("1"));
        user.setEmail("123456@qq.com");

        return user;
    }

    @GetMapping("/user")
    public User getUser() {
        User user = new User();
        user.setName("Defalut User");
        user.setGender(new Byte("1"));
        user.setEmail("123456@qq.com");

        return user;
    }


}