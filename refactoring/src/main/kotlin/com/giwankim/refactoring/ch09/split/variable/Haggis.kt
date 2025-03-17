package com.giwankim.refactoring.ch09.split.variable

import kotlin.math.min

fun distanceTravelled(
    scenario: Scenario,
    time: Int,
): Double {
    var result: Double
    var acc = scenario.primaryForce / scenario.mass
    val primaryTime = min(time, scenario.delay)
    result = 0.5 * acc * primaryTime * primaryTime
    val secondaryTime = time - scenario.delay
    if (secondaryTime > 0) {
        val primaryVelocity = acc * scenario.delay
        acc = (scenario.primaryForce + scenario.secondaryForce) / scenario.mass
        result += primaryVelocity * secondaryTime + 0.5 * acc * secondaryTime * secondaryTime
    }
    return result
}
