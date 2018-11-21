package com.yf.af.data;

import com.yf.af.data.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chen on 2018/5/7.
 */

public interface UserService {
    List<User> getUser();
}
