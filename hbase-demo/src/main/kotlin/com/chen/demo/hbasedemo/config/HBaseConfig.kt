package com.chen.demo.hbasedemo.config

import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//hbase:
//  zookeeper:
//    quorum: ip-127.0.0.1
//  property:
//      clientPort: 2181

@Configuration
class HBaseConfig {

    @Value("\${hbase.zookeeper.quorum}")
    var quorum: String? = null

    @Value("\${hbase.property.clientPort}")
    var clientPort: String? = null

    @Bean
    fun hbaseTemplate(): HbaseTemplate {
        val conf = org.apache.hadoop.conf.Configuration()
        conf.set("hbase.zookeeper.quorum", quorum)
        conf.set("hbase.zookeeper.property.clientPort", clientPort)
        return HbaseTemplate(conf)
    }
}
