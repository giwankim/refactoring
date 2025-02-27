package com.giwankim.refactoring.chapter06.combine.functions.into.classes.client1

import com.giwankim.refactoring.chapter06.combine.functions.into.classes.acquireReading
import java.time.Month
import java.time.Year

fun baseRate(
    month: Month,
    year: Year,
): Double = 0.1

fun main() {
    val reading = acquireReading()
    val baseCharge = baseRate(reading.month, reading.year) * reading.quantity
}
