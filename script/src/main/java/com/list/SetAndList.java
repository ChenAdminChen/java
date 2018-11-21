package com.list;

import org.junit.Test;

import java.util.*;

/**
 * Created by chen on 2017/7/28.
 */
public class SetAndList {

    /**
     * <p>测试set的查询速度</p>
     * time:18282210
     * find time:10886330
     */
    @Test
    public void setTest1(){
        Set<Integer> set=new HashSet<>();

        Map<String,Object> map=new HashMap();

        long startTime=System.nanoTime();
        for(int i=0;i<50000;i++){
            set.add(i);
        }

        long endTime=System.nanoTime();

        long time=endTime-startTime;

        System.out.println("time:" +time);

        startTime=System.nanoTime();
        for(Iterator<Integer> iterator = set.iterator(); iterator.hasNext();){

            if(iterator.next().equals("9867")){
                System.out.println("dnal");
            }
        }
        endTime=System.nanoTime();

        time=endTime-startTime;

        System.out.println("find time:" +time);
    }

    /**
     * <p>经过测试 list的使用时间</p>
     * time:5369517
     * find time:6973964
     */
    @Test
    public void listTest1(){
        List<Integer> list=new ArrayList<>();

        long startTime=System.nanoTime();

        for(int i=0;i<50000;i++){
            list.add(i);
        }

        long endTime=System.nanoTime();

        long time=endTime-startTime;

        System.out.println("time:" +time);

        startTime=System.nanoTime();
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals("9867"));
        }
        endTime=System.nanoTime();

        time=endTime-startTime;

        System.out.println("find time:" +time);
    }
}
