package com.chen.zuul.servicezuul.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;

//@Configuration
public class MyConfiguration {
//    @Bean
//    LoadBalancedBackOffPolicyFactory backOffPolicyFactory() {
//        return new LoadBalancedBackOffPolicyFactory() {
//            @Override
//            public BackOffPolicy createBackOffPolicy(String service) {
//                return new ExponentialBackOffPolicy();
//            }
//        };
//    }
}
