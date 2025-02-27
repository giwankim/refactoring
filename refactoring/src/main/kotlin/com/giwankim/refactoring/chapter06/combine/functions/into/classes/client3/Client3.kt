package com.giwankim.refactoring.chapter06.combine.functions.into.classes.client3

import com.giwankim.refactoring.chapter06.combine.functions.into.classes.Reading
import com.giwankim.refactoring.chapter06.combine.functions.into.classes.acquireReading
import com.giwankim.refactoring.chapter06.combine.functions.into.classes.client1.baseRate
import com.giwankim.refactoring.chapter06.combine.functions.into.classes.client2.taxThreshold
import java.time.Month
import java.time.Year
import kotlin.math.max

fun baseRate(
    month: Month,
    year: Year,
): Double = 0.1

fun taxThreshold(year: Year) = 1000.0

fun calculateBaseCharge(reading: Reading): Double = baseRate(reading.month, reading.year) * reading.quantity

fun main() {
    val reading = acquireReading()
    val base = baseRate(reading.month, reading.year) * reading.quantity
    val taxableCharge = max(0.0, base - taxThreshold(reading.year))
}
