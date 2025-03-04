package com.giwankim.refactoring.ch06.combine.functions.into.transform

import java.time.Month
import java.time.Year

val reading = Reading("ivan", 10, Month.of(5), Year.of(2017))

fun acquireReading() = reading

fun baseRate(
    month: Month,
    year: Year,
): Double = 0.1

fun taxThreshold(year: Year) = 1000.0
