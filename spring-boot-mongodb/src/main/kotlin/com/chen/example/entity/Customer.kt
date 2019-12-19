package com.chen.example.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

//@RepositoryRestResource(collectionResourceRel = "people", path = "people")
@Document(collection = "customer")
data class Customer(@Id
                    var id: String? = null,

                    var name: String? = null,

                    var age: Int? = 0,

                    var cars: MutableList<String>? = mutableListOf(),

                    var email: String? = null,

                    var address: String? = null,

                    var other: LinkedHashMap<String, String>? = linkedMapOf()
)

//@Document(collection = "customer")
//class Customer : LinkedHashMap<String, Any>()