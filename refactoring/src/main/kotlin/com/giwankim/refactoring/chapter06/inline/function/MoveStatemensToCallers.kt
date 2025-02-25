package com.giwankim.refactoring.chapter06.inline.function.move.statements.to.callers

import com.giwankim.refactoring.chapter06.inline.function.Customer

fun reportLines(customer: Customer): List<List<String>> {
    val lines: MutableList<List<String>> = mutableListOf()
    lines.add(listOf("name", customer.name))
    gatherCustomerData(lines, customer)
    return lines
}

fun gatherCustomerData(
    out: MutableList<List<String>>,
    customer: Customer,
) {
    out.add(listOf("location", customer.location))
}
