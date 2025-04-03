package com.giwankim.refactoring.ch11.replace.query.with.parameter

class HeatingPlan(
    val max: Int,
    val min: Int,
) {
    val targetTemperature: Int
        get() {
            val selectedTemperature = Thermostat.selectedTemperature
            return if (selectedTemperature > max) {
                max
            } else if (selectedTemperature < min) {
                min
            } else {
                selectedTemperature
            }
        }
}
