package com.observer.pattern;

public class Client {
    public static void main(String[] args) {
        WeatherDate subject = new WeatherDate();

        CurrentConditionDisplay ccd = new CurrentConditionDisplay(subject);

        ForecastConditionDisplay ffd=new ForecastConditionDisplay(subject);

        //改变被观察者的状态、观察者做出显示调整
        subject.setMeasurements(19, 40, 20);
    }
}
