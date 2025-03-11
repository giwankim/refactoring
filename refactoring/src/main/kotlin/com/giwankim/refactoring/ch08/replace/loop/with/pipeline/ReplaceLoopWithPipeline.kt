package com.giwankim.refactoring.ch08.replace.loop.with.pipeline

fun acquireData(input: String): List<Office> {
    val lines = input.lines()
    var firstLine = true
    val result: MutableList<Office> = mutableListOf()
    val loopItems = lines
    for (line in loopItems) {
        if (firstLine) {
            firstLine = false
            continue
        }
        if (line.isBlank()) {
            continue
        }
        val record = line.split(",")
        if (record[1].trim() == "India") {
            result.add(Office(city = record[0].trim(), phone = record[2].trim()))
        }
    }
    return result
}
