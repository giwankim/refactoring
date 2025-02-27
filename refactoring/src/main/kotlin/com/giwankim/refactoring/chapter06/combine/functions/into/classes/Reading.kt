package com.giwankim.refactoring.chapter06.combine.functions.into.classes

import com.giwankim.refactoring.chapter06.combine.functions.into.classes.refactor.client3.baseRate
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
}
