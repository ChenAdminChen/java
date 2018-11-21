package com.zookeeper.client

import org.apache.zookeeper.CreateMode
import org.apache.zookeeper.Watcher
import org.apache.zookeeper.Watcher.Event.KeeperState
import org.apache.zookeeper.ZooDefs
import org.apache.zookeeper.ZooKeeper
import org.apache.zookeeper.data.Stat
import java.io.IOException
import java.util.concurrent.CountDownLatch


class Executor {
    private var zoo: ZooKeeper? = null
    var connectionLatch = CountDownLatch(1)

    @Throws(IOException::class, InterruptedException::class)
    fun connect(host: String): ZooKeeper {
        zoo = ZooKeeper(host, 2000, Watcher { we ->
            if (we.state == KeeperState.SyncConnected) {
                connectionLatch.countDown()
            }
        })

        connectionLatch.await()
        return zoo as ZooKeeper
    }

    @Throws(InterruptedException::class)
    fun close() {
        zoo!!.close()
    }
}

fun main(args: Array<String>) {

    var executor = Executor()
    var zoo = executor.connect("localhost:2181")

//    zoo.create("/chen", "test".toByteArray(), ZooDefs.Ids.READ_ACL_UNSAFE, CreateMode.PERSISTENT )

    val z = zoo.getData("/chen", false, Stat()).toString()

    print(z)

    executor.close()
    print(zoo)

}