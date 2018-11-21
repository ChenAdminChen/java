@file:Suppress("DEPRECATION")

package kafka

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.util.*


class Consumer {
}

/**
 * kafka 接收消息
 */
fun main(args:Array<String>){
    val props = Properties()
    props["bootstrap.servers"] = "localhost:9092"
    props["group.id"] = "test"
    props["key.deserializer"] = StringDeserializer::class.java.name
    props["value.deserializer"] = StringDeserializer::class.java.name

    val consumer = KafkaConsumer<String,String>(props)
    consumer.subscribe(Arrays.asList("test", "java"))
    while (true) {
        val records = consumer.poll(100)
        for (record in records)
            System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value())
    }

}