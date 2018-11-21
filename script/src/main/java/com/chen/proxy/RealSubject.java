package com.chen.proxy;

/**
 * Created by chen on 2017/7/6.
 */

/**
 * 真实的对象
 */
public class RealSubject extends Subject{
    public RealSubject(){}

    public void request(){
        System.out.println("from real subject");
    }
}


