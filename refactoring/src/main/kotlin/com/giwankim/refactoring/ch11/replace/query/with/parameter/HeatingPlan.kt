package com.giwankim.refactoring.ch11.replace.query.with.parameter

class HeatingPlan(
    val max: Int,
    val min: Int,
) {
    val targetTemperature: Int
        get() {
            return if (Thermostat.selectedTemperature > max) {
                max
            } else if (Thermostat.selectedTemperature < min) {
                min
            } else {
                Thermostat.selectedTemperature
            }
        }
}
