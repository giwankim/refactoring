package com.giwankim.refactoring.chapter06.introduce.parameter.objects.refactor

import com.giwankim.refactoring.chapter06.introduce.parameter.objects.Reading
import com.giwankim.refactoring.chapter06.introduce.parameter.objects.Station

fun readingsOutsideRange(
    station: Station,
    range: NumberRange,
): List<Reading> = station.readings.filter { it.temp in range.min..range.max }
