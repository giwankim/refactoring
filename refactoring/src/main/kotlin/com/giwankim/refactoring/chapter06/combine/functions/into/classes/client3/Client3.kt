package com.giwankim.refactoring.chapter06.combine.functions.into.classes.client3

import com.giwankim.refactoring.chapter06.combine.functions.into.classes.Reading
import com.giwankim.refactoring.chapter06.combine.functions.into.classes.acquireReading
import java.time.Month
import java.time.Year

fun baseRate(
    month: Month,
    year: Year,
): Double = 0.1

fun calculateBaseCharge(reading: Reading): Double = baseRate(reading.month, reading.year) * reading.quantity

fun main() {
    val reading = acquireReading()
    val basicChargeAmount = calculateBaseCharge(reading)
}
