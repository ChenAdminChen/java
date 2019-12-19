package com.chen.example

import com.chen.example.Repository.CustomerRepository
import com.chen.example.entity.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class SpringBootMongodbApplication : CommandLineRunner {
    @Autowired
    private val repository: CustomerRepository? = null

    override fun run(vararg args: String?) {

//        repository!!.deleteAll()

        // save a couple of customers
        // save a couple of customers
//        repository!!.save(Customer(name = "Alice", age = 22))
//        repository!!.save(Customer(name = "Bob", age = 23))

        // fetch all customers
        // fetch all customers
        println("Customers found with findAll():")
        println("-------------------------------")

        for (customer in repository!!.findAll()) {
            println(customer)
        }

        println()

        // fetch an individual customer
        // fetch an individual customer
        println("Customer found with findByFirstName('Alice'):")
        println("--------------------------------")
        println(repository.findByName("Alice"))

//        println("Customers found with findByLastName('Smith'):")
//        println("--------------------------------")
//        for (customer in repository.findByLastName("Smith")) {
//            println(customer)
//        }

    }

}

fun main(args: Array<String>) {
    runApplication<SpringBootMongodbApplication>(*args)
}
