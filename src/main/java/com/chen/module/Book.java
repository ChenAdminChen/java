package com.chen.module;

import java.io.Serializable;

/**
 * Created by chen on 2018/3/21.
 */
public class Book implements Serializable{

    //序列版本号，一个唯一标识号，
    // 若未指定，则会根据该类的名称及实现的接口及公有变量和私有变量的自动生成一个唯一标识号，但若该类中的属性改变，反序列化则会失败
    //若类内的内容改变后，需要保证原来的内容，能反序列化，同时保证新产生的序列化能被原来的类反序列化，因此带来的许多测试功能
    private static final long serialVersionUID = 1L;

    private String name;
    private int price;

    //transient private BookType bookType;

    private BookType bookType;

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Book(String name, int price, BookType bookType) {
        this.name = name;
        this.price = price;
        this.bookType = bookType;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", bookType=" + bookType +
                '}';
    }

}
