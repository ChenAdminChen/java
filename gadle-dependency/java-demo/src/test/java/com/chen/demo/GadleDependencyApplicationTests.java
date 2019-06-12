package com.chen.demo;

import org.junit.Test;

public class GadleDependencyApplicationTests {

    @Test
    public void contextLoads() {
        JavaUser javaUser = new JavaUser();
        javaUser.setId(2);

        assert (javaUser.getId() == 2);
    }

}
