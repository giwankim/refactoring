package com.giwankim.refactoring.chapter06.combine.functions.into.classes

fun main() {
    val reading = acquireReading()
    val baseCharge = baseRate(reading.month, reading.year) * reading.quantity
}
