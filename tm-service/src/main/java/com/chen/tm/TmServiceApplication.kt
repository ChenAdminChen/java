package com.chen.tm

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 *事务控制管理中心
 */

/**
 * 依赖的包
 * spring-boot-start-actuator -> 暴露自身模块信息
 * spring-boot-configuration-processor -> 使用xml/properties时添加的依赖
 * hessian -> 面向对象的消息通信
 * protostuff-core、protostuff -> 序列化与反序列化
 * kryo-shaded ->反序列化中的一种
 * guava -> google提供的一个工具包，其中包括了（cache/concurrent/hash/reffect/annotations/eventbus）
 *
 */

@SpringBootApplication
@EnableTransactionManagerServer
class TmServiceApplication

fun main(args: Array<String>) {
    runApplication<TmServiceApplication>(*args)
}
