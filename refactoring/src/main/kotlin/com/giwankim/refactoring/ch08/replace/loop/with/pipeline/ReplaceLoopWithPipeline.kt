package com.giwankim.refactoring.ch08.replace.loop.with.pipeline

fun acquireData(input: String): List<Office> {
    val lines = input.lines()
    val result: MutableList<Office> = mutableListOf()
    val loopItems =
        lines
            .drop(1)
            .filter { it.isNotBlank() }
            .map { it.split(",") }
    for (line in loopItems) {
        val record = line
        if (record[1].trim() == "India") {
            result.add(Office(city = record[0].trim(), phone = record[2].trim()))
        }
    }
    return result
}
