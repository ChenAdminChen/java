package com.list;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chen on 2017/7/27.
 */
public class TestList {

    /**
     *  数组转换为ArrayList
     *  Arrays.asList()方法可以得到一个ArrayList集合，
     *  但是得到这个ArrayList是定义在Arrays类中的一个私有的静态内部类。
     *  这个类虽然和java.util.ArrayList同名，但是并不是同一个类。
     *  java.util.Arrays.ArrayList类中实现了set(), get(), contains()等方法，
     *  但是并没有定义向其中增加元素的方法。所以Arrays.asList()得到的ArrayList的大小是固定的。
     *  若向其中添加元素，会抛出Java.lang.UnsupportedOperationException的错误
     *
     * 输出结果如下
     *f2		fd		fg		fgg
     */
    @Test
   public void listTest1(){

        String arr[]=new String[]{"f2","fd","fg","fgg"};

        List<String> list1= Arrays.asList(arr);

        //不支持次操作  java.lang.UnsupportedOperationException
        //list1.add("fddd");

        list1.forEach(value->{
            System.out.print(value+"\t\t");
        });
   }

    /**
     * 将数组转换成一个真正的集合，该集合中能添加元素，
     *
     * 输出结果如下
     * f2			fd			fg			fgg			fffff
     */
   @Test
   public void listTest2(){
       String arr[]=new String[]{"f2","fd","fg","fgg"};

       ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(arr));

       //此处的元素能添加进去
       arrayList.add("fffff");

       arrayList.forEach(value->
               System.out.print(value+"\t\t\t")
       );
   }
}
