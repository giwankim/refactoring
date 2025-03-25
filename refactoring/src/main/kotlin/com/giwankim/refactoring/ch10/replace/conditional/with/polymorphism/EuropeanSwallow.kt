package com.giwankim.refactoring.ch10.replace.conditional.with.polymorphism

class EuropeanSwallow(
    name: String,
    numberOfCoconuts: Int,
    voltage: Double,
    isNailed: Boolean,
) : Bird(name, "EuropeanSwallow", numberOfCoconuts, voltage, isNailed) {
    override val plumage: Plumage
        get() = Plumage.AVERAGE
}
