package com.chen.demo;


import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

public class Mains {

    //需要toBlocking
    // 按type分组 求type出现的次数（count） 求type相同时number的和（result）
    public static void main(String[] args) {
        Demo demo = new Demo(1, 2);
        Demo demo1 = new Demo(1, 2);
        Demo demo2 = new Demo(2, 2);
        Demo demo3 = new Demo(1, 2);
        Demo demo4 = new Demo(3, 2);
        Demo demo5 = new Demo(2, 2);
        Demo demo6 = new Demo(1, 4);
        Demo demo7 = new Demo(3, 3);


        List<Demo> list = new ArrayList<>();
        list.add(demo);
        list.add(demo1);
        list.add(demo2);
        list.add(demo3);
        list.add(demo4);
        list.add(demo5);
        list.add(demo6);
        list.add(demo7);

        List<Demo> demos = new ArrayList<>();

        Observable.fromIterable(list)
                .sorted()
                .groupBy(x -> x.getType())
                .flatMap(r -> r.toList().toObservable())
                .map(r ->
                        {
                            Demo d = r.get(0);

                            d.setCount(r.size());

//                            if(r.size() >= 2){
                            for (int i = 0; i < r.size(); i++) {
                                d.setResult(d.getResult() + r.get(i).getNumber());
                            }
//                            }
                            return d;

                        }
                ).blockingIterable()
                .forEach(r -> list.set(list.size(), r));

        for (int i =0; i< list.size();i++){
            System.out.println(list.get(i));
        }
    }
}


