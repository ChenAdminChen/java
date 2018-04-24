package com.completable.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by chen on 2017/7/31.
 */
public class Client {
    public static void main(String[] args){
        Shop shop = new Shop("BestShop");
        long start = System.currentTimeMillis();
        Future<Double> future = shop.getPriceAsync("My Favorite");
        long invocationTime = System.currentTimeMillis() - start;
        System.out.println("调用接口时间：" + invocationTime + "毫秒");

        doSomethingElse();

        try {
            double price = future.get();

            System.out.println("price"+price);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        long retrievalTime = System.currentTimeMillis() - start;
        System.out.println("返回价格消耗时间：" + retrievalTime + "毫秒");

    }

    public static void doSomethingElse(){
        System.out.println("做其他的事情。。。");
    }
}
