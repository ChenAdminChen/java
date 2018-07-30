package com.example.demo.controller

import com.example.demo.utils.CacheData
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/single")
class SinglePatternController {

    val cacheData = CacheData

    @GetMapping("/cache-data")
    fun getSingleCacheData(): CacheData {
        return cacheData
    }

    @PostMapping("/cache-data")
    fun addCache(@RequestParam(name="name")name: String, @RequestParam(name="status")status: Boolean):String {

        //若cacheData.array为空则返回
        //cacheData.array?:return "error"

        cacheData.list!!.add(name)
        cacheData.status = true

        return "success"
    }
}