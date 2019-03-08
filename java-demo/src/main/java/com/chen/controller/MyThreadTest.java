package com.chen.controller;

public class MyThreadTest {
    public static void main(String[] args) {
        Thread t1= new Thread(new MyThread());
        Thread t2= new Thread(new MyThread());

        t1.start();
        t2.start();
    }
}
