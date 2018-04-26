package com.chen.test;

/**
 * Created by chen on 2018/4/20.
 */
public class SuperMan extends Person implements ActionInterface {

    private boolean BlueBriefs;

    public void fly(){

        System.out.println("超人会fly---");
    }

    public boolean isBlueBriefs(){
        return BlueBriefs;
    }

    public void setBlueBriefs(boolean blueBriefs){
        BlueBriefs = blueBriefs;
    }

    public void walk(int m) {
        System.out.println("超人会走耶----走了" + m + "米就走不动了！");
    }
}

interface ActionInterface{
    public void walk(int m);
}
