package com.giwankim.refactoring.chapter06.inline.function.move.statements.to.callers

import com.giwankim.refactoring.chapter06.inline.function.Customer

fun reportLines(customer: Customer): List<List<String>> {
    val lines: MutableList<List<String>> = mutableListOf()
    lines.add(listOf("name", customer.name))
    lines.add(listOf("location", customer.location))
    return lines
}
