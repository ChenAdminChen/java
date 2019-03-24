package com.chen.demo;

public class Demo implements Comparable<Demo> {
    private int type = 0;
    private int number = 0;

    private int result;

    private int count;

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public int getType() {
        return type;
    }

    public Demo() {
    }

    public Demo(int type, int number) {
        this.type = type;
        this.number = number;
    }

    @Override
    public int compareTo(Demo demo) {
        if (this.type == demo.getType()) {
            return 0;
        }
        return 1;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "type=" + type +
                ", number=" + number +
                ", result=" + result +
                ", count=" + count +
                '}';
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
