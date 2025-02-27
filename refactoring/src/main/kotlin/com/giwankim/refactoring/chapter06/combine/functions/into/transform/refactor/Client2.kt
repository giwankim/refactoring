package com.giwankim.refactoring.chapter06.combine.functions.into.transform.refactor

import com.giwankim.refactoring.chapter06.combine.functions.into.transform.acquireReading
import com.giwankim.refactoring.chapter06.combine.functions.into.transform.taxThreshold

fun main() {
    val rawReading = acquireReading()
    val reading = enrichReading(rawReading)
    val taxableCharge = Math.max(0.0, reading.baseCharge - taxThreshold(reading.year))
}
