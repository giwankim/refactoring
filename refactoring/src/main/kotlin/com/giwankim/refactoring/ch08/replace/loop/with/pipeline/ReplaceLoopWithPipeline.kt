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
            .map { Office(city = it[0].trim(), phone = it[2].trim()) }
    for (line in loopItems) {
        result.add(line)
    }
    return result
}
