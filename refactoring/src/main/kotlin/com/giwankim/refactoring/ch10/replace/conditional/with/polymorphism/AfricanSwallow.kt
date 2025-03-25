package com.giwankim.refactoring.ch10.replace.conditional.with.polymorphism

class AfricanSwallow(
    name: String,
    numberOfCoconuts: Int,
    voltage: Double,
    isNailed: Boolean,
) : Bird(name, "AfricanSwallow", numberOfCoconuts, voltage, isNailed) {
    override val plumage: Plumage
        get() = if (numberOfCoconuts > 2) Plumage.TIRED else Plumage.AVERAGE

    override val airSpeedVelocity: Double
        get() = 40.0 - 2 * numberOfCoconuts
}
