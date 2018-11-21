package com.array;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.*;

/**
 * Created by chen on 2017/7/27.
 */
public class TestArray {
    private String str[] = new String[]{"as", "ss", "fe", "rr", "ty","ff","cc","rt","er","ert","ffew","qq","fet"};

    /**
     * <p>使用for循环查询数组中是否存在某个值</p>
     * time:112067
     */
    @Test
    public void TestArray1() {

        long startTime = System.nanoTime();

        for (int i = 0; i < str.length; i++) {
            if (str[i].equals("fe")) {
                System.out.println(str[i] + "\t\t" + i);
            }
        }

        long endTime = System.nanoTime();

        long time = endTime - startTime;
        System.out.println("time:" + time);
    }

    /**
     * <p>使用set查询数组中是否包括某个值 </p>
     * time:63800
     */
    @Test
    public void TestArray2() {
        long startTime = System.nanoTime();
        Set<String> set = new HashSet<String>(Arrays.asList(str));

        boolean b = set.contains("fe");


        long endTime = System.nanoTime();

        System.out.println(b);

        long time = endTime - startTime;
        System.out.println("time:" + time);
    }

    /**
     * <p>使用list查询数组中是否包括某个值 ，Arrays.binarySearch()方法只能用于有序数组</p>
     * time:44383
     */
    @Test
    public void TestArray3() {
        long startTime = System.nanoTime();
        List<String> list = new ArrayList<>(Arrays.asList(str));

        boolean b = list.contains("fe");


        long endTime = System.nanoTime();

        System.out.println(b);

        long time = endTime - startTime;
        System.out.println("time:" + time);
    }

    /**
     * <p>使用Arrays.binarySearch进行查询</p>
     * time:22746
     */
    @Test
    public void TestArray4() {
        long startTime = System.nanoTime();

        int b = Arrays.binarySearch(str, "fe");

        long endTime = System.nanoTime();

        System.out.println(b);

        long time = endTime - startTime;
        System.out.println("time:" + time);
    }

    /**
     * <p>使用ArrayUtils.contains()查询数组中的是否包括某个值</p>
     * time:4760637
     */
    @Test
    public void TestArray5() {
        long startTime = System.nanoTime();
        boolean b=  ArrayUtils.contains(str, "fe");
        long endTime = System.nanoTime();

        System.out.println(b);

        long time = endTime - startTime;
        System.out.println("time:" + time);
    }
}
