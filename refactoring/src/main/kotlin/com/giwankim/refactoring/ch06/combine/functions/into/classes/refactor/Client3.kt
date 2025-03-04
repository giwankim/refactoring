package com.giwankim.refactoring.ch06.combine.functions.into.classes.refactor

import com.giwankim.refactoring.ch06.combine.functions.into.classes.acquireReading

fun main() {
    val reading = acquireReading()
    val basicChargeAmount = reading.baseCharge
    val taxableCharge = reading.taxableCharge
}
