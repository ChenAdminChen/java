package com.yf.af.ehcahe;

import com.yf.af.data.DaoManagement;
import com.yf.af.data.model.User;

import java.util.List;

/**
 * Created by chen on 2018/5/7.
 */
public class EhcacheServiceImpl implements EhcacheService {

    private DaoManagement daoManagement;

    public void setDaoManagement(DaoManagement daoManagement) {
        this.daoManagement = daoManagement;
    }

    public void getUser() {
        com.yf.af.data.UserService userService = (com.yf.af.data.UserService) daoManagement.getService("data.userService");

        List<User> users =  userService.listUser();

    }
}
