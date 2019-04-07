package com.chen.pool

import java.util.*

class DefinitionThreadPool(count: Int) {
    var threadPoolFunction: ThreadPoolFunction? = null

    var stackThread = Stack<Thread>()

    // init x threads storage stack
    init {
        for (i in 1..count) {
            val thread = Thread()
            val runnable = Runnable{
                val threadName = Thread.currentThread().name

                threadPoolFunction!!.executor()
            }

            runnable.run()

            stackThread.add(thread)
        }
    }

    fun executorThreadPool(threadPoolFunction: ThreadPoolFunction) {

        this.threadPoolFunction = threadPoolFunction

        stackThread.peek().start()
    }
}

interface ThreadPoolFunction {
    fun executor()
}