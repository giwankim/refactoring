package com.giwankim.refactoring.chapter06.combine.functions.into.classes.refactor

import com.giwankim.refactoring.chapter06.combine.functions.into.classes.acquireReading
import com.giwankim.refactoring.chapter06.combine.functions.into.classes.taxThreshold
import kotlin.math.max

fun main() {
    val reading = acquireReading()
    val taxableCharge = max(0.0, reading.baseCharge - taxThreshold(reading.year))
}
