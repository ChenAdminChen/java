package com.observer.pattern;

import java.util.ArrayList;
import java.util.List;

public class WeatherDate implements Subject{
    // Observer数组 可以简单想一下为什么不用LinkedList
    private List<Observer> observers = new ArrayList<Observer>();
    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int index = observers.indexOf(observer);
        if (index >= 0) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++) {
            Observer o = (Observer) observers.get(i);
            o.update(temperature, humidity, pressure);
        }
    }

    /**
     * 模仿数据变动时自动触发notifyObserver函数
     */
    public void measurementsChanged() {
        notifyObserver();
    }

    /**
     * 模仿数据变动、即当我们调用这个方法时就说明数据有改变、 这样所有的观察者都会被通知
     */
    public void setMeasurements(float temperature, float humidity,
                                float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
