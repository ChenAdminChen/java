package com.yf.af;

import com.yf.af.data.DaoManagement;
import com.yf.af.data.UserService;
import com.yf.af.data.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by chen on 2018/5/7.
 */
public class ServerHandler {
    //private static Logger log = LoggerFactory.getLogger(ServerHandler.class);

    private DaoManagement daoManagement;

    public void setDaoManagement(DaoManagement daoManagement) {
        this.daoManagement = daoManagement;
    }

    public void getUser(int number, int time){
        UserService userService = (UserService) daoManagement.getService("data.userService");
        Random random = new Random(new Date().getTime());

        if(number >0 ){
            for (int i = 0; i < number; i++) {

                Instant instant = Instant.now();
                List<User> users = userService.getUser();
                Instant instantEnd = Instant.now();

                System.out.println("time: " + (instantEnd.getNano() - instant.getNano()));

                System.out.println("i:  "+ i + ", get user number: " + users.size());

                try {
                    Thread.sleep(random.nextInt(13) + 7);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    return;
                }

            }
        }

        //if (users.size() > 0){
        //    for (int i = 0; i < users.size(); i++) {
        //
        //        System.out.println(users.get(i).toString());
        //    }
        //}
    }

    public void testComm(){
        System.out.println("成功");
    }
}
