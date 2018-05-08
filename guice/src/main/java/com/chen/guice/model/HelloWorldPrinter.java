package com.chen.guice.model;

import com.chen.guice.guice.MyApplet;
import com.chen.guice.guice.MyDestination;
import com.chen.guice.guice.StringProvider;

public class HelloWorldPrinter implements MyApplet{
    private MyDestination printStream;
    private StringProvider stringProvider;
    public HelloWorldPrinter(MyDestination printStream, StringProvider stringProvider) {
        super();
        this.printStream = printStream;
        this.stringProvider = stringProvider;
    }

    private void printlnHelloWorld() {
        //system.out 向那里输出
        //println 怎么样输出
        //hello world 输出的内容

        //System.out.println("fff");
         printStream.write(stringProvider.get());
    }

    public void run(){
         printlnHelloWorld();
    }
}
