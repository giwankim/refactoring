package com.giwankim.refactoring.ch10.replace.conditional.with.polymorphism

open class Bird(
    val name: String,
    val type: String,
    val numberOfCoconuts: Int,
    val voltage: Double,
    val isNailed: Boolean,
) {
    open val plumage: Plumage
        get() {
            return when (type) {
                "EuropeanSwallow" -> error("Should be unreachable")
                "AfricanSwallow" -> error("Should be unreachable")
                "NorwegianBlueParrot" -> error("Should be unreachable")
                else -> Plumage.UNKNOWN
            }
        }

    open val airSpeedVelocity: Double
        get() {
            return when (type) {
                "EuropeanSwallow" -> 35.0
                "AfricanSwallow" -> 40.0 - 2 * numberOfCoconuts
                "NorwegianBlueParrot" -> if (isNailed) 0.0 else 10 + voltage / 10.0
                else -> Double.MIN_VALUE
            }
        }
}
