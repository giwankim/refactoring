package com.giwankim.refactoring.ch10.replace.conditional.with.polymorphism

class Bird(
    val name: String,
    val type: String,
    val numberOfCoconuts: Int,
    val voltage: Double,
    val isNailed: Boolean,
) {
    val plumage: Plumage
        get() {
            return when (type) {
                "EuropeanSwallow" -> Plumage.AVERAGE
                "AfricanSwallow" -> if (numberOfCoconuts > 2) Plumage.TIRED else Plumage.AVERAGE
                "NorwegianBlueParrot" -> if (voltage > 100) Plumage.SCORCHED else Plumage.BEAUTIFUL
                else -> Plumage.UNKNOWN
            }
        }

    val airSpeedVelocity: Double
        get() {
            return when (type) {
                "EuropeanSwallow" -> 35.0
                "AfricanSwallow" -> 40.0 - 2 * numberOfCoconuts
                "NorwegianBlueParrot" -> if (isNailed) 0.0 else 20 + voltage / 10.0
                else -> Double.MIN_VALUE
            }
        }
}
