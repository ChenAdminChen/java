package kafka

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

class Producer {
}

/**
 * kafka 发送消息
 */
fun main(args: Array<String>) {
    var props = Properties()
    props["bootstrap.servers"] = "localhost:9092"
//    props["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
    props["key.serializer"] = StringSerializer::class.java.name
    props["value.serializer"] = StringSerializer::class.java.name

    var producer: KafkaProducer<String, String>? = null
    var recode: ProducerRecord<String, String>? = null

    recode = ProducerRecord("test", "keys")

    producer = KafkaProducer(props)

    producer.send(recode)

    producer.close()
}