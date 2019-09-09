package com.chen.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class KeySpaceNotificationMessageListener implements MessageListener {

    @Autowired
    RedisCacheManager redisCacheManager;

    @Autowired
    RedisConfig redisConfig;

    @Autowired
    RedisTemplate stringRedisTemplate;

    @PostConstruct
    public void init() {
        redisConfig.setKeySpaceNotificationMessageListener(this);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {

        System.out.println("Recieved action = " + new String(message.getBody()) + " " +
                " and key info = " + new String(message.getChannel()));

        System.out.printf(stringRedisTemplate.opsForValue().get(message.getBody().toString()).toString());
        /*
         * just printing the message here. You can do anything you wish with the
         * received info. For example you can publish it to UI clients using redis
         * pub sub or DeepStream Client.
         * */

    }

}