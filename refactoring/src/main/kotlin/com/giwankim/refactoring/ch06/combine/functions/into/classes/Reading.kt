package com.giwankim.refactoring.ch06.combine.functions.into.classes

import java.time.Month
import java.time.Year

data class Reading(
    val customer: String,
    val quantity: Int,
    val month: Month,
    val year: Year,
) {
    val baseCharge: Double
        get() = baseRate(month, year) * quantity
    val taxableCharge: Double
        get() = Math.max(0.0, baseCharge - taxThreshold(year))
}
