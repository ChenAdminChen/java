package com.chen.DynamicProxy;

/**
 * Created by chen on 2017/7/6.
 */

/**
 * 真正的对象
 */
public class RealSubject implements Subject {
    public RealSubject(){}

    public void request(String name,int age){

        System.out.println("from real subject's request" +"   name:"+name+"  age:"+age);


    }

    public void getRequest(){
        System.out.println("from real subject's getRequest ");
    }
}
