package com.giwankim.refactoring.ch09.change.value.to.reference

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

object CustomerRepository {
    private val customers: ConcurrentMap<Long, Customer> = ConcurrentHashMap()

    fun registerCustomer(id: Long) = customers.putIfAbsent(id, Customer().apply { this.id = id })

    fun findCustomer(id: Long): Customer? = customers[id]
}
