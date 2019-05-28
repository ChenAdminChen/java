package com.chen.annotation.annotationdemo.controller;

import com.chen.annotation.anno.PermissionDependency;
import com.chen.annotation.anno.PermissionModule;
import com.chen.annotation.anno.PermissionRequest;
import com.chen.annotation.annotationdemo.entity.User;

import java.util.ArrayList;
import java.util.List;

@PermissionModule(id = "user", name = "用户模块", parent = "specialist")
public class UserController {
    @PermissionRequest(PermissionRequest.Permission.Read)
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();

        return list;
    }

    @PermissionRequest(PermissionRequest.Permission.New)
    public void createUser() {
        List<User> list = new ArrayList<>();

        System.out.println("成功");
    }

    @PermissionRequest(PermissionRequest.Permission.Update)
    public void updateUser() {
        List<User> list = new ArrayList<>();

        System.out.println("成功");
    }
}
