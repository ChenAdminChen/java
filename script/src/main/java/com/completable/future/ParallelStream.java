package com.completable.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by chen on 2017/7/31.
 */
public class ParallelStream {
    public static void main(String[] args) {
        /*long start = System.currentTimeMillis();
        System.out.println(findPrice("java8实战"));
        long duration = System.currentTimeMillis() - start;
        System.out.println("总消耗时间：" + duration + "毫秒");
*/
       long start = System.currentTimeMillis();
        System.out.println(findPrice2("java8实战"));
        long duration = System.currentTimeMillis() - start;
        System.out.println("总消耗时间："+duration +"毫秒");
    }

    public static List<String> findPrice(String product) {
        List<Shop> shops = Arrays.asList(new Shop("sunjin.org"),
                new Shop("加瓦匠"),
                new Shop("京东商城"),
                new Shop("天猫商城"));
        return shops.parallelStream()
                .map(shop -> String.format("%s 的价格是 %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());

    }

    public static List<String> findPrice2(String product) {
            List<Shop> shops = Arrays.asList(new Shop("sunjin.org"),
                    new Shop("加瓦匠"),
                    new Shop("京东商城"),
                    new Shop("天猫商城"));

        List<CompletableFuture<String>> priceFuture = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync( // 使用异步的方式计算每种商品的价格
                        () -> shop.getName() + " 的价格是 " + shop.getPrice(product)))
                .collect(toList());

        return priceFuture.stream()
                .map(CompletableFuture::join) //join 操作等待所有异步操作的结果
                .collect(toList());
    }
}
