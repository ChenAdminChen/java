package com.chen.proxy;

/**
 * Created by chen on 2017/7/6.
 */
public class ProxySubject extends Subject {

    //以真实角色作为代理的属性
    private Subject realSubject;

    public ProxySubject(RealSubject subject){
        this.realSubject=subject;
    }

    @Override
    public void request() {

        System.out.println("from proxy subject");


        realSubject.request();


    }
}
