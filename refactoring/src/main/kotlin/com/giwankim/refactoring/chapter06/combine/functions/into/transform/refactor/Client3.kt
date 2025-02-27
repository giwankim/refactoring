package com.giwankim.refactoring.chapter06.combine.functions.into.transform.refactor

import com.giwankim.refactoring.chapter06.combine.functions.into.transform.Reading
import com.giwankim.refactoring.chapter06.combine.functions.into.transform.acquireReading
import com.giwankim.refactoring.chapter06.combine.functions.into.transform.baseRate

fun enrichReading(original: Reading): Reading {
    fun calculateBaseCharge(reading: Reading) = baseRate(reading.month, reading.year) * reading.quantity

    val result = original.copy()
    result.baseCharge = calculateBaseCharge(result)
    return result
}

fun main() {
    val rawReading = acquireReading()
    val reading = enrichReading(rawReading)
    val basicChargeAmount = reading.baseCharge
}
