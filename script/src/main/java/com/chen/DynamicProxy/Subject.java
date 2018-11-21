package com.chen.DynamicProxy;

/**
 * 抽象角色
 */
public  interface Subject {
     public void request(String name,int age);

     public void getRequest();
}