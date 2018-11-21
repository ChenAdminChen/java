package com.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chen on 2017/7/27.
 */
public class ArrayAndLinkedList {

    /**
     * <p>在ArrayList中添加值</p>
     */
    @Test
    public void arrayList1() {

        List<Integer> list = new ArrayList<>();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            list.add(i);
        }
        long endTime = System.currentTimeMillis();

        long time = endTime - startTime;

        System.out.println("time:"+time);

        startTime=System.nanoTime();
        list.remove(3);
        endTime=System.nanoTime();

        time=endTime-startTime;
        System.out.println(" remove time:"+time);
    }

    /**
     * <p>在LinkedList中添加值</p>
     * 
     */
    @Test
    public void linkedLists() {
        LinkedList<Integer> list = new LinkedList<>();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            list.add(i);
        }
        long endTime = System.currentTimeMillis();

        long time = endTime - startTime;

        System.out.println("time:"+time);


        startTime=System.nanoTime();
        list.remove(3);
        endTime=System.nanoTime();

        time=endTime-startTime;
        System.out.println(" remove time:"+time);
    }
}
