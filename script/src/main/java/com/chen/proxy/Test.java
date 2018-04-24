package com.chen.proxy;

/**
 * Created by chen on 2017/7/6.
 */
public class Test {
    public static void main(String[] args) {
        RealSubject realSubject=new RealSubject();

        ProxySubject proxySubject=new ProxySubject(realSubject);

        proxySubject.request();
    }
}
