package com.chen.controller;

import com.chen.module.Person;

import java.io.*;

/**
 * Created by chen on 2018/3/21.
 *
 * ObjectInutStream ObjectOutputStream 对象序列化原理
 *
 * 1、方便进行网络传输，远程方法调用，或者写到硬盘中
 *
 * 2、使用最多的是将一个对象转化成二进制字节流，不序列化不能转，或者字节流再转回java对象的时候，类没有序列化不能转
 */
public class IOTest {
    public static void main(String[] args){
        writeObject();

        //readObj();
    }


    /**
     * 写入流对象到指定的文件内
     */
    public static void writeObject(){

        try{

            //new objectOutputStream object
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("G:\\person.obj", true));

            objectOutputStream.writeObject(new Person("Jaskson", 23, "男" ));
            objectOutputStream.writeObject(new Person("Job", 20, "女" ));

            objectOutputStream.close();

        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    /**
     * 读入指定流对象
     */
    public static void readObj()
    {
        try {
            ObjectInputStream objin = new ObjectInputStream(new FileInputStream("G:\\person.obj"));
            Person p = null;

            while ((p = (Person) objin.readObject()) != null) {
                System.out.println(p.toString());
                System.out.println(p.getAge()  + p.getName() + p.getSex());
            }

            objin.close();
            System.out.println(p);
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
