package com.giwankim.refactoring.chapter06.combine.functions.into.classes.refactor.client3

import com.giwankim.refactoring.chapter06.combine.functions.into.classes.acquireReading
import java.time.Month
import java.time.Year

fun baseRate(
    month: Month,
    year: Year,
): Double = 0.1

fun main() {
    val reading = acquireReading()
    val basicChargeAmount = reading.baseCharge
}
