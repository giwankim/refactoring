package com.giwankim.refactoring.ch06.combine.functions.into.classes

fun calculateBaseCharge(reading: Reading): Double = baseRate(reading.month, reading.year) * reading.quantity

fun main() {
    val reading = acquireReading()
    val basicChargeAmount = calculateBaseCharge(reading)
}
