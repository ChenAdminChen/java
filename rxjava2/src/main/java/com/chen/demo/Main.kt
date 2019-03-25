package com.chen.demo

import io.reactivex.Flowable

// 需要toBlocking
// 按type分组 求type出现的次数（count） 求type相同时number的和（result）
fun main(args: Array<String>) {
    val list = listOf(
        Data(1, 2),
        Data(1, 2),
        Data(2, 2),
        Data(1, 2),
        Data(3, 2),
        Data(2, 2),
        Data(1, 4),
        Data(3, 3)
    )

    Flowable.fromIterable(list)
        .groupBy { it.type }
        .flatMap {
            it.reduce { t1, t2 ->
                Data(it.key!!, t1.value + t2.value)
            }
//                (Data(it.key!!, 0)) { d1, d2 ->
//                    d1.value += d2.value
//                    d1
//                }
                .toFlowable()
        }
        .blockingForEach {
            println(it)
        }
}

data class Data(
    var type: Int,
    var value: Int
)
