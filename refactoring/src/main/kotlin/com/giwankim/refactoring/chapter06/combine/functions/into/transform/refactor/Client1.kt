package com.giwankim.refactoring.chapter06.combine.functions.into.transform.refactor

import com.giwankim.refactoring.chapter06.combine.functions.into.transform.acquireReading

fun main() {
    val rawReading = acquireReading()
    val reading = enrichReading(rawReading)
    val baseCharge = reading.baseCharge
}
