package com.chen.swagger.controller

import com.chen.swagger.module.User
import io.swagger.annotations.Api
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
@Api(value = "用戶身份管理", description = "用戶身份管理操作API", tags = ["UserApi"], consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(
//        ApiResponse(code = 200, message = "OK"),
//        ApiResponse(code=201,message = "post ok"),
//        ApiResponse(code = 400, message = "客户端请求错误"),
//        ApiResponse(code = 404, message = "找不到资源"),
//        ApiResponse(code = 500, message = "运行时异常"),
        ApiResponse(code = 422, message = "请求格式正确，请求中可能存在语法问题"),
        ApiResponse(code = 417, message = "检查出数据有问题")
)
class RestController {

    @GetMapping("/user")

    fun getUserInfo(): ResponseEntity<User> {
        var user = User()
        user.age = 23;
        user.id = 3
        user.name = "test"

        return ResponseEntity.ok(user);
    }
}