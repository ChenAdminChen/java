package com.chen.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by chen on 2017/7/6.
 */
public class Client {
    public static void main(String[] args) throws Throwable {
        RealSubject rs = new RealSubject();


        InvocationHandler ds = new DynamicSubject(rs);

        //用到了反射
        Class cls = rs.getClass();

        //在转调具体目标对象之前，可以执行一些功能处理

        //转调具体目标对象的方法
        Subject subject = (Subject) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), ds);

        subject.request("xt",2);

        subject.getRequest();

        //在转调具体目标对象之后，可以执行一些功能处理
    }
}
