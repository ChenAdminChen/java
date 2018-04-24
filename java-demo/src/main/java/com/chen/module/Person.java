package com.chen.module;

import java.io.Serializable;

/**
 * Created by chen on 2018/3/21.
 */
public class Person implements Serializable{
    private String name;
    private int age;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     *  person(string name, int age, String sex)
     * @param name
     * @param age
     * @param sex
     */
    public Person(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}
