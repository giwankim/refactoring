package com.giwankim.refactoring.chapter06.combine.functions.into.transform

fun main() {
    val reading = acquireReading()
    val baseCharge = baseRate(reading.month, reading.year) * reading.quantity
}
