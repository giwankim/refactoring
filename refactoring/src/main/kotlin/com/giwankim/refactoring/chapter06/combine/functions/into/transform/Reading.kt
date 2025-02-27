package com.giwankim.refactoring.chapter06.combine.functions.into.transform

import java.time.Month
import java.time.Year

data class Reading(
    val customer: String,
    val quantity: Int,
    val month: Month,
    val year: Year,
)
