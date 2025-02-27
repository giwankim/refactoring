package com.giwankim.refactoring.chapter06.combine.functions.into.transform.refactor

import com.giwankim.refactoring.chapter06.combine.functions.into.transform.Reading
import com.giwankim.refactoring.chapter06.combine.functions.into.transform.acquireReading
import com.giwankim.refactoring.chapter06.combine.functions.into.transform.baseRate

fun enrichReading(original: Reading): Reading = original.copy()

fun calculateBaseCharge(reading: Reading) = baseRate(reading.month, reading.year) * reading.quantity

fun main() {
    val rawReading = acquireReading()
    val reading = enrichReading(rawReading)
    val basicChargeAmount = calculateBaseCharge(reading)
}
