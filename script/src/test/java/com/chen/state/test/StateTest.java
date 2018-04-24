package com.chen.state.test;

import com.chen.state.State;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by chen on 2017/7/7.
 */
public class StateTest {
    State state;

    @Before
    public void setUp() throws Exception {
        state = new State("init");

        State stateGreen = new State("green");
        State stateRed = new State("red");
        State stateYellow = new State("yellow");

        state.addTrans("init", stateGreen);

        stateGreen.addTrans("panic", stateRed)
                .addTrans("warn", stateYellow);

        stateRed.addTrans("clear", stateGreen)
                .addTrans("calm", stateYellow);

        stateYellow.addTrans("panic", stateRed)
                .addTrans("clear", stateGreen);
    }

    @Test
    public void testStateMachine() {
        state.doTrans("init")
                .doTrans("panic")
                .doTrans("calm")
                .doTrans("clear")
                .doTrans("calm");
    }

}
