package com.giwankim.refactoring.ch06.combine.functions.into.transform.refactor

import com.giwankim.refactoring.ch06.combine.functions.into.transform.acquireReading

fun main() {
    val rawReading = acquireReading()
    val reading = enrichReading(rawReading)
    val basicChargeAmount = reading.baseCharge
}
