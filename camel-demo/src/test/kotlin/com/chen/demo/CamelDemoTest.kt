package com.chen.demo

import org.junit.jupiter.api.Test

class CamelDemoTest {
    @Test
    fun testCamel1() {
        from("direct:name")
                .transform(constant("Claus"))
                .to("mock:result");

// Simulate a slow http service (delaying 1 sec) we want to invoke async
        from("jetty:http://0.0.0.0:%s/myservice", getPort())
                .delay(1000)
                .transform(constant("Bye World"))
                .to("mock:result");
    }
}