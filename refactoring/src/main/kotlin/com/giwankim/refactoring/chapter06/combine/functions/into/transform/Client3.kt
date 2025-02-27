package com.giwankim.refactoring.chapter06.combine.functions.into.transform

fun calculateBaseCharge(reading: Reading): Double = baseRate(reading.month, reading.year) * reading.quantity

fun main() {
    val reading = acquireReading()
    val basicChargeAmount = calculateBaseCharge(reading)
}
