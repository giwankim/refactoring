package com.giwankim.refactoring.ch06.inline.function.example2

fun reportLines(customer: Customer): List<List<String>> {
    val lines: MutableList<List<String>> = mutableListOf()
    gatherCustomerData(lines, customer)
    return lines
}

fun gatherCustomerData(
    out: MutableList<List<String>>,
    customer: Customer,
) {
    out.add(listOf("name", customer.name))
    out.add(listOf("location", customer.location))
}
