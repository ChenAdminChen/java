package com.chen.demo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

//@JsonIgnoreProperties(ignoreUnknown = true)
data class User(val name: String? = null, val age: String? = null, var address: List<Address>? = mutableListOf()) {


}

data class Address(val address: String? = null, val phone: String? = null, var number: Int? = 0) {

}