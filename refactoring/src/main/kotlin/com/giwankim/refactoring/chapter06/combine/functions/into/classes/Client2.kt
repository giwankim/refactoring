package com.giwankim.refactoring.chapter06.combine.functions.into.classes

import kotlin.math.max

fun main() {
    val reading = acquireReading()
    val base = baseRate(reading.month, reading.year) * reading.quantity
    val taxableCharge = max(0.0, base - taxThreshold(reading.year))
}
