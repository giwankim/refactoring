package com.giwankim.refactoring.chapter06.combine.functions.into.classes

import java.time.Month
import java.time.Year

data class Reading(
    val customer: String,
    val quantity: Int,
    val month: Month,
    val year: Year,
)

val reading = Reading("ivan", 10, Month.of(5), Year.of(2017))

fun acquireReading() = reading
