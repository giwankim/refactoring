package com.giwankim.refactoring.chapter06.introduce.parameter.objects

fun readingsOutsideRange(
    station: Station,
    min: Int,
    max: Int,
): List<Reading> = station.readings.filter { it.temp in min..max }
