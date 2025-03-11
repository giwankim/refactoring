package com.giwankim.refactoring.ch08.replace.loop.with.pipeline

fun acquireData(input: String): List<Office> {
    val lines = input.lines()
    return lines
        .drop(1)
        .filter { line -> line.isNotBlank() }
        .map { line -> line.split(",") }
        .filter { fields -> fields[1].trim() == "India" }
        .map { fields -> Office(city = fields[0].trim(), phone = fields[2].trim()) }
}
