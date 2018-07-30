package com.example.demo.utils

/**
 * Created by liufeng on 2018/7/25
 */
class Message<T> {

    var data: T? = null
    var result: Int = 0
    var desc: String? = null
    var extras: MutableMap<String, Any>? = null

    constructor()

    constructor(data: T?, result: Int, desc: String?, extras: MutableMap<String, Any>?) {
        this.data = data
        this.result = result
        this.desc = desc
        this.extras = extras
    }

    constructor(data: T?) {
        this.data = data
    }


    companion object {
        const val RESULT_SUCCESS = 0
        const val RESULT_ADD_FAILURE = 0XF5 // 245
        const val RESULT_DELETE_FAILURE = 0XF6 // 246
        const val RESULT_UPDATE_FAILURE = 0XF7 // 247
        const val RESULT_QUERY_DATA_NULL = 0XFA // 250
        const val RESULT_QUERY_FAILURE = 0XF8 // 248

    }
}