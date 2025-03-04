package com.giwankim.refactoring.ch06.introduce.parameter.objects.refactor

import com.giwankim.refactoring.ch06.introduce.parameter.objects.Reading
import com.giwankim.refactoring.ch06.introduce.parameter.objects.Station

fun readingsOutsideRange(
    station: Station,
    range: NumberRange,
): List<Reading> = station.readings.filter { !range.contains(it.temp) }
