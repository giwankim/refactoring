package com.giwankim.refactoring.ch09.split.variable

import kotlin.math.min

fun distanceTravelled(
    scenario: Scenario,
    time: Int,
): Double {
    var result: Double
    val primaryAcceleration = scenario.primaryForce / scenario.mass
    val primaryTime = min(time, scenario.delay)
    result = 0.5 * primaryAcceleration * primaryTime * primaryTime
    val secondaryTime = time - scenario.delay
    if (secondaryTime > 0) {
        val primaryVelocity = primaryAcceleration * scenario.delay
        var acc = (scenario.primaryForce + scenario.secondaryForce) / scenario.mass
        result += primaryVelocity * secondaryTime + 0.5 * acc * secondaryTime * secondaryTime
    }
    return result
}
