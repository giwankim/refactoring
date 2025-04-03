package com.giwankim.refactoring.ch11.replace.query.with.parameter

class HeatingPlan(
    val max: Int,
    val min: Int,
) {
    fun xxNEWtargetTemperature(selectedTemperature: Int): Int =
        if (selectedTemperature > max) {
            max
        } else if (selectedTemperature < min) {
            min
        } else {
            selectedTemperature
        }
}
