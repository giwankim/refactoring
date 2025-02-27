package com.giwankim.refactoring.chapter06.combine.functions.into.transform

import java.time.Month
import java.time.Year
import kotlin.properties.Delegates

data class Reading(
    val customer: String,
    val quantity: Int,
    val month: Month,
    val year: Year,
) {
    var baseCharge: Double by Delegates.notNull()
    var taxableCharge: Double by Delegates.notNull()
}
