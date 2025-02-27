package com.giwankim.refactoring.chapter06.combine.functions.into.transform

fun main() {
    val reading = acquireReading()
    val base = baseRate(reading.month, reading.year) * reading.quantity
    val taxableCharge = Math.max(0.0, base - taxThreshold(reading.year))
}
