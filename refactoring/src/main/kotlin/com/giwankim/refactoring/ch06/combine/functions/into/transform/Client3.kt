package com.giwankim.refactoring.ch06.combine.functions.into.transform

fun calculateBaseCharge(reading: Reading) = baseRate(reading.month, reading.year) * reading.quantity

fun main() {
    val reading = acquireReading()
    val basicChargeAmount = calculateBaseCharge(reading)
}
