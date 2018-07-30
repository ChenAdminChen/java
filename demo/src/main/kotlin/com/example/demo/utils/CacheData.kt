package com.example.demo.utils

/**
 * cacheData 为单例类
 * object declaration 第一次使用时创建
 */
object CacheData {

    //静态
    object Factory {
       val Cache_Name = "cache name"
    }


    var status: Boolean? = null

    var list: MutableList<String>? = null

    init {

        list = mutableListOf()
    }
}