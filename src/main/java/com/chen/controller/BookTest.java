package com.chen.controller;

import com.chen.module.Book;
import com.chen.module.BookType;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by chen on 2018/3/21.
 *
 * 当序列化对象中包括没有序列化的对象，则无法对序列化的对象进行序列化
 * 标记某个属性不序列化：
 *      可以用transient来标识，另一个方法可以在类里重写readObject和writeObject
 *
 *      error: java.io.NotSerializableException: com.chen.module.BookType
 *      reason：Book class 中bookType class not is Serializable
 */
public class BookTest {
     public static void main(String args[]) {
            //writeObject();
         readObject();
      }

      //对象序列化,保存到某个文件内
    public static void writeObject(){
         try{
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("G:\\Book.obj",true));

             BookType bookType = new BookType("计算机");

             objectOutputStream.writeObject(new Book("JavaScript进阶", 53, bookType));

             objectOutputStream.writeObject(new Book("Java进阶", 43, bookType));

             objectOutputStream.close();
         }catch (Exception e){
             System.out.println(e.toString());
         }
    }

    /**
     *
     */
    public static void readObject(){
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("G:\\Book.obj"));

            Book book = null;

            while ( ( book = ( Book)  objectInputStream.readObject()) != null){
                System.out.println(book.toString());

            }

        }catch (Exception e){
            System.out.println(e.toString());
        };
    }
}
