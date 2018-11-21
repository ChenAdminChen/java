package kafka

import kafka.admin.AdminUtils
import kafka.admin.RackAwareMode
import kafka.utils.ZkUtils
import org.apache.kafka.common.security.JaasUtils
import java.util.*
import kafka.server.ConfigType
import java.security.KeyStore


class CreateTopic {
    /**
     * 比较遗憾地是，不管是创建topic还是删除topic，目前Kafka实现的方式都是后台异步操作的，而且没有提供任何回调机制或返回任何结果给用户，
     * 所以用户除了捕获异常以及查询topic状态之外似乎并没有特别好的办法可以检测操作是否成功。
     */
    //create topic
    fun createTopic() {
        val zookeeperHosts = "localhost:2181" // If multiple zookeeper then -> String zookeeperHosts = "192.168.20.1:2181,192.168.20.2:2181";
        val sessionTimeOutInMs = 15 * 1000 // 15 secs
        val connectionTimeOutInMs = 10 * 1000 // 10 secs

        var zkUtils = ZkUtils.apply(zookeeperHosts, sessionTimeOutInMs, connectionTimeOutInMs, JaasUtils.isZkSecurityEnabled())

        val topicName = "testTopic/test"
        val noOfPartitions = 2  //分区
        val noOfReplication = 3
        val topicConfiguration = Properties()

        AdminUtils.createTopic(zkUtils, topicName, noOfPartitions, noOfReplication, topicConfiguration, RackAwareMode.`Enforced$`.`MODULE$`)
    }

    //delete topic
    fun deleteTopic() {
        val zookeeperHosts = "localhost:2181" // If multiple zookeeper then -> String zookeeperHosts = "192.168.20.1:2181,192.168.20.2:2181";
        val sessionTimeOutInMs = 15 * 1000 // 15 secs
        val connectionTimeOutInMs = 10 * 1000 // 10 secs

        val zkUtils = ZkUtils.apply(zookeeperHosts, sessionTimeOutInMs, connectionTimeOutInMs, JaasUtils.isZkSecurityEnabled())
        //删除topic 't1'
        AdminUtils.deleteTopic(zkUtils, "t1")
        zkUtils.close()
    }

    //select topic
    fun selectTopic() {
        val zookeeperHosts = "localhost:2181" // If multiple zookeeper then -> String zookeeperHosts = "192.168.20.1:2181,192.168.20.2:2181";
        val sessionTimeOutInMs = 15 * 1000 // 15 secs
        val connectionTimeOutInMs = 10 * 1000 // 10 secs

        val zkUtils = ZkUtils.apply(zookeeperHosts, sessionTimeOutInMs, connectionTimeOutInMs, JaasUtils.isZkSecurityEnabled())
        // 获取topic 'test'的topic属性属性
        val props:Properties = AdminUtils.fetchEntityConfig(zkUtils, ConfigType.Topic(), "test")


        val it = props.entries.iterator()
//        props.
        // 查询topic-level属性
//        val it = props.entrySet.iterator()

        while (it.hasNext()) {
            val entry = it.next() as KeyStore.Entry
            print(entry)
        }
//        zkUtils.close()

    }

}

fun main(args: Array<String>) {

    val createTopic = CreateTopic()

    createTopic.createTopic()

}