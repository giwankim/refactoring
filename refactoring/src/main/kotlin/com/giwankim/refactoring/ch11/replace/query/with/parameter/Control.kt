package com.giwankim.refactoring.ch11.replace.query.with.parameter

fun setToHeat() {
    // set to heat
}

fun setToCool() {
    // set to cool
}

fun setOff() {
    // set to off
}

fun control(plan: HeatingPlan) {
    if (plan.xxNEWtargetTemperature(Thermostat.selectedTemperature) > Thermostat.currentTemperature) {
        setToHeat()
    } else if (plan.xxNEWtargetTemperature(Thermostat.selectedTemperature) < Thermostat.currentTemperature) {
        setToCool()
    } else {
        setOff()
    }
}
