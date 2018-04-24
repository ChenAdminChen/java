package com.chen.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestStream {

    /**
     * 数值流的构造
     */
    @Test
    public void streamInt() {

        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);

        System.out.println();

        IntStream.range(1, 3).forEach(System.out::println);
        System.out.println();

        IntStream.rangeClosed(1, 3).forEach(System.out::println);
        System.out.println();
    }

    /**
     * 流转换为其它数据结构
     */
    @Test
    public void streamTransfrom() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().
                map(n -> n * n).
                collect(Collectors.toList());

        squareNums.forEach(value ->
                System.out.print(value + "\t\t")
        );

        System.out.println();

        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());

        outputStream.forEach(value ->
                System.out.println(value));
    }

    @Test
    public void tectonicFlow() {
        // 1. Individual values
        Stream stream = Stream.of("a", "b", "c");
        System.out.println(stream);

        // 2. Arrays
        String[] strArray = new String[]{"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        System.out.println(stream.toString());

        // 3. Collections
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();
        System.out.println(stream.toString());
    }

    /**
     *stream 中 peek的用法
     */
    @Test
    public void streamPeek() {
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    public void streamPrint(){
        //List<Integer> nums = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);

//        System.out.println("sum is:"+nums.stream().filter(num -> num != null).
//                distinct().mapToInt(num -> num * 2).
//                peek(System.out::println).skip(2).limit(4).sum());
    }
}
