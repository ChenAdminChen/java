package com.chen.example.Repository

import com.chen.example.entity.Customer
import org.springframework.data.mongodb.repository.MongoRepository

interface CustomerRepository : MongoRepository<Customer, Any> {

    fun findByName(firstName: String): Customer

    fun findByAge(age: Int): List<Customer?>?
}