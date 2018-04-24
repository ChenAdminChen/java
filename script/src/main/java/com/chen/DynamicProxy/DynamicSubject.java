package com.chen.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by chen on 2017/7/6.
 */
public class DynamicSubject implements InvocationHandler {

    private Object obj;  //存放需要代理的对象

    public DynamicSubject(){}

    public DynamicSubject(Object obj){
        this.obj=obj;
    }

    /**
     * 执行目标对象的方法
     * JDK实现代理只需要使用newProxyInstance方法,但是该方法需要接收三个参数
     * @param proxy  指定当前目标对象使用类加载器,获取加载器的方法是固定的
     * @param method  所需要的调用的方法
     * @param args  该方法中的参数
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        /**
         * 动态代理 可以用于使用不同包的中的类时 对调用的方法 需要在该方法 前加动作 或者方法执行后加动作都可以用此实现
         */
        if((method.toString()).contains("Subject.request")){

            System.out.println("before calling "+method );

            args[0]=args[0]+"fff";

            args[1]=Integer.parseInt(args[1]+"")+1;

            method.invoke(obj,args);

            System.out.println("after calling "+method);

        }else{

            System.out.println("can defferences method");

            System.out.println("before calling "+method);

            method.invoke(obj,args);

            System.out.println("after calling "+method);
        }

        return null;
    }
}
