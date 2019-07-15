package com.chen.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "用户信息")
public class User {

    private String name;
    @ApiModelProperty(required = true, value = "age", dataType = "int", example = "123")
    @NotNull
    @Min(value = 3, message = "最小值必须大于3")

    private Integer age;
    private Integer id;

}
