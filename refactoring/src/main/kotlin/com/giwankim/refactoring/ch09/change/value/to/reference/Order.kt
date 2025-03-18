package com.giwankim.refactoring.ch09.change.value.to.reference

class Order(
    val number: Int,
    val customer: Customer,
) {
    constructor(number: Int, customerId: Long) : this(number, CustomerRepository.findCustomer(customerId)!!)
}
