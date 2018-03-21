package com.chen.module;

import java.io.Serializable;

/**
 * Created by chen on 2018/3/21.
 */
public class Book implements Serializable{
    //private static final long serialVersionUID = 1L;

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
