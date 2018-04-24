package com.chen.state;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chen on 2017/7/7.
 */
public class State {
    String name;

    /**
     * key = event
     */
    Map<String, State> trans = new HashMap<>();

    public State(String name) {
        this.name = name;
    }

    public State addTrans(String event, State newState) {
        trans.put(event, newState);
        return this;
    }

    public State doTrans(String event) {
        State newState = trans.get(event);
        if (newState == null) {
            System.out.println("invalid event: " + event);
            return null;
        }

        System.out.println("trans-ed to: " + newState + " by " + event);
        return newState;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                '}';
    }
}
