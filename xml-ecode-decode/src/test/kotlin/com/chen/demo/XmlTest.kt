package com.chen.demo

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import org.junit.Test

class XmlTest {

    @Test
    fun xmlConvertObject() {
        val xml = """<xml>
    <name>chen</name>
    <age>12</age>
    <address_0>address_0xxxx</address_0>
    <phone_0>phone_0</phone_0>
    <address_1>address_1xxxx</address_1>
    <phone_1>phone_1</phone_1>
    <address_2>address_1xxxx</address_2>
    <phone_3>phone_3</phone_3>
</xml>"""

        val xmlMapper = XmlMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val type = object : TypeReference<Map<String, Any>>() {}

        val map = xmlMapper.readValue(xml, type)

        val list = map.filter {
            Regex("[0-9]+").matches(it.key.toString().split("_").last())
        }
            .toList()
            .groupBy { it.first.split("_")[1] }
            .map {
                it.value.map { p ->
                    Pair(p.first.split("_").toList().dropLast(1).joinToString("_"), p.second)
                }.toMap()
            }.map {
                println(it)
                Address(address = it["address"].toString(), phone = it["phone"].toString())
            }.toList()

        val user = xmlMapper.readValue(xml, User::class.java)
        user.address = list
    }

    @Test
    fun objectConvertXml() {

    }
}