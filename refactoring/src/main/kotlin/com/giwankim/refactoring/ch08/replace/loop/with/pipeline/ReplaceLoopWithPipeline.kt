package com.giwankim.refactoring.ch08.replace.loop.with.pipeline

fun acquireData(input: String): List<Office> {
    val lines = input.lines()
    val result: MutableList<Office> = mutableListOf()
    val loopItems =
        lines
            .drop(1)
            .filter { it.isNotBlank() }
            .map { it.split(",") }
            .filter { it[1].trim() == "India" }
    for (line in loopItems) {
        val record = line
        result.add(Office(city = record[0].trim(), phone = record[2].trim()))
    }
    return result
}
