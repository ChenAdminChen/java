package com.chen.demo;

import lombok.Data;

@Data
public class JavaUser implements User {
    private Integer id;
    private String name;
    private String password;


}
