package com.chen.demo.service;

import com.chen.demo.entity.User;
import io.swagger.annotations.*;
import org.apache.cxf.annotations.Provider;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
@Path("/access-token")
@Provider(Provider.Type.InInterceptor)

@Api(value = "Hello resource Version 1", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public interface UserService {
    @GET
    @Path("/")
    @ApiOperation(value = "Gets a hello resource")
    Response getAccessToken(@QueryParam("cred") String credentials,
                            @QueryParam("pwd") String password,
                            @ApiParam(name = "vd", value = "用户vd", required = true) @QueryParam("vd") String validation,
                            @ApiParam(name = "ir", value = "用户ir", required = true) @QueryParam("ir") int isRememberAccount
    );

    @PUT
    @Path("/")
    @ApiOperation(value = "Gets a hello resource")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token",
            required = true, dataType = "string", paramType = "header")})
    Response updateAccessToken(@ApiParam(name = "user", value = "用户", required = true) User user);

}
