package com.giwankim.refactoring.ch11.preserve.whole.objects

class HeatingPlan(
    val temperatureRange: Range,
) {
    fun xxNEWwithinRange(range: Range): Boolean = temperatureRange.low >= range.low && temperatureRange.high <= range.high
}
