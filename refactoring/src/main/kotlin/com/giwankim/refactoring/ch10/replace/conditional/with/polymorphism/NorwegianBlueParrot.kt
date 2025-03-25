package com.giwankim.refactoring.ch10.replace.conditional.with.polymorphism

class NorwegianBlueParrot(
    name: String,
    numberOfCoconuts: Int,
    voltage: Double,
    isNailed: Boolean,
) : Bird(name, "NorwegianBlueParrot", numberOfCoconuts, voltage, isNailed) {
    override val plumage: Plumage
        get() = if (voltage > 100) Plumage.SCORCHED else Plumage.BEAUTIFUL

    override val airSpeedVelocity: Double
        get() = if (isNailed) 0.0 else 10 + voltage / 10.0
}
