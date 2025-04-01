package com.giwankim.refactoring.ch11.preserve.whole.objects

class HeatingPlan(
    val temperatureRange: Range,
) {
    fun withinRange(
        bottom: Int,
        top: Int,
    ): Boolean = temperatureRange.low >= bottom && temperatureRange.high <= top

    fun xxNEWwithinRange(range: Range): Boolean = withinRange(bottom = range.low, top = range.high)
}
