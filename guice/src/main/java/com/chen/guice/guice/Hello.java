package com.chen.guice.guice;

public class Hello {
    /**
     * bootstrap:
     parse command line
     set up enviroment
     kick off main logic

     * @param args
     */
    public static void main(String[] args) {

        MyApplet mainApplet = Configuration.getMainApplet();
        mainApplet.run();

    }

}
