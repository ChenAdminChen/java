package com.chen.guice.guice;

import com.chen.guice.model.HelloWorldPrinter;

public class Configuration {
    public static MyApplet getMainApplet() {
        return new HelloWorldPrinter(new StringWriterApplet(System.out), new StringProvider() {
            @Override
            public String get() {
                return "hello world";
            }
        });
    }
}
