package com.example.demo.controller

import com.example.demo.entity.Shift
import com.example.demo.entity.ShiftStat
import com.example.demo.entity.User
import com.example.demo.service.ShiftService
import com.example.demo.utils.CacheData

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import rx.Observable

import com.example.demo.utils.Message

@RestController
@RequestMapping("/shift")
class Shiftcontroller {

    @Value("\${rest.path}")
    lateinit var path: String

    val cacheData = CacheData

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Autowired
    lateinit var shiftService: ShiftService
//    @Autowired
//    lateinit var shiftDaoService: ShiftDaoService

    @GetMapping("/show-shift")
    fun showShift(): Message<List<Shift>> {
        var message: Message<List<Shift>> = Message()
        val shifts: List<Shift> = shiftService.getShiftAll()

        message.data = shifts
        return message
    }

    @GetMapping("/show-user")
    fun showUser(): Message<List<User>> {
        var message: Message<List<User>> = Message()

        val user = User("chen", 1, 12)

        val list = mutableListOf<User>()
        list.add(user)

        message.data = list
        return message
    }


    @PostMapping("/")
    fun addShift(@RequestBody shifts: List<Shift>): String {
        print(shifts)
        return ""
    }


    @GetMapping("/show-index-data")
    fun showIndexData() {
//        val url = "$path/"
//
//        val result = restTemplate.getForObject("https://yifenganxin.com/cxf/oss/token", Message::class.java)
//
//        var shifts: List<ShiftStat> = result!!.data as List<ShiftStat>


        var shiftStats: MutableList<ShiftStat> = mutableListOf()

        //id: Int?, indexId: Int?, data: Float?, time: String?, shift: Int?
        ShiftStat(2, 13, 450.0f, "2017-02-22", 1)

        shiftStats.add(ShiftStat(2, 13, 40.0f, "2017-02-22", 1))

        shiftStats.add(ShiftStat(4, 14, 50.0f, "2017-02-22", 2))
        shiftStats.add(ShiftStat(5, 16, 451.0f, "2017-02-22", 2))

        shiftStats.add(ShiftStat(8, 33, 454.0f, "2017-02-22", 2))
        shiftStats.add(ShiftStat(9, 51, 44.0f, "2017-02-22", 2))

        shiftStats.add(ShiftStat(3, 15, 4590.0f, "2017-02-22", 1))

        shiftStats.add(ShiftStat(6, 73, 452.0f, "2017-02-23", 1))
        shiftStats.add(ShiftStat(7, 83, 453.0f, "2017-02-23", 1))



        shiftStats.add(ShiftStat(10, 57, 424.0f, "2017-02-23", 2))

        shiftStats.add(ShiftStat(11, 23, 354.0f, "2017-02-24", 2))

        shiftStats.add(ShiftStat(12, 43, 4754.0f, "2017-02-21", 2))


        var test :MutableList<MutableList<ShiftStat>> = mutableListOf()

        Observable.from(shiftStats)

                .sorted { it1, it2 ->

                    val result = it1.time.compareTo(it2.time)
                    if (result == 0)
                        it1.shift - it2.shift
                    else
                        result

                }

                .groupBy { it.time }

                .flatMap { it.groupBy { it.shift } }

                .forEach {

                    var array :MutableList<ShiftStat> = mutableListOf()

                    it.asObservable().forEach {

                        println(it.toString())

                        array.add(array.size, it)
                    }
                    test.add(test.size,array)
                }

        test.forEach {
            println(it)
        }
    }

    @PostMapping("/cache-data")
    fun addCache(@RequestParam(name="name")name: String, @RequestParam(name="status")status: Boolean):String {

        //若cacheData.array为空则返回
        //cacheData.array?:return "error"

//        cacheData.array!![cacheData.array!!.size] = age
        cacheData.list!!.add(name)
        cacheData.status = true

        return "success"
    }
}


