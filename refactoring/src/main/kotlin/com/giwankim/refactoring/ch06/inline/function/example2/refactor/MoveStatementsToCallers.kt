package com.giwankim.refactoring.ch06.inline.function.example2.refactor

import com.giwankim.refactoring.ch06.inline.function.example2.Customer

fun reportLines(customer: Customer): List<List<String>> {
    val lines: MutableList<List<String>> = mutableListOf()
    lines.add(listOf("name", customer.name))
    lines.add(listOf("location", customer.location))
    return lines
}
