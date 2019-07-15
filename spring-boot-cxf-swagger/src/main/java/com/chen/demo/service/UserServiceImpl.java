package com.chen.demo.service;

import com.chen.demo.entity.User;
import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

@Component

public class UserServiceImpl implements UserService {
    @Override
    public Response getAccessToken(String credentials, String password, String validation, int isRememberAccount) {
        Response.ResponseBuilder responseBuilder = Response.status(Response.Status.OK);
        return responseBuilder.build();
    }

    @Override
    public Response updateAccessToken(User user) {


        return Response.ok(user).build();
    }
}
