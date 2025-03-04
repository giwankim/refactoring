package com.giwankim.refactoring.ch06.combine.functions.into.classes.refactor

import com.giwankim.refactoring.ch06.combine.functions.into.classes.Reading
import com.giwankim.refactoring.ch06.combine.functions.into.classes.acquireReading
import com.giwankim.refactoring.ch06.combine.functions.into.classes.taxThreshold
import kotlin.math.max

fun main() {
    val reading = acquireReading()
    val taxableCharge = taxableChargeFn(reading)
}

fun taxableChargeFn(reading: Reading): Double = max(0.0, reading.baseCharge - taxThreshold(reading.year))
