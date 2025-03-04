package com.giwankim.refactoring.ch06.combine.functions.into.transform.refactor

import com.giwankim.refactoring.ch06.combine.functions.into.transform.Reading
import com.giwankim.refactoring.ch06.combine.functions.into.transform.baseRate
import com.giwankim.refactoring.ch06.combine.functions.into.transform.taxThreshold

fun enrichReading(original: Reading): Reading {
    fun calculateBaseCharge(reading: Reading) = baseRate(reading.month, reading.year) * reading.quantity

    val result = original.copy()
    result.baseCharge = calculateBaseCharge(result)
    result.taxableCharge = Math.max(0.0, result.baseCharge - taxThreshold(result.year))
    return result
}
