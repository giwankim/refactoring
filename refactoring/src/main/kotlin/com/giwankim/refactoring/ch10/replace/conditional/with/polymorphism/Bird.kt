package com.giwankim.refactoring.ch10.replace.conditional.with.polymorphism

open class Bird(
    val name: String,
    val type: String,
    val numberOfCoconuts: Int,
    val voltage: Double,
    val isNailed: Boolean,
) {
    open val plumage: Plumage
        get() = Plumage.UNKNOWN

    open val airSpeedVelocity: Double
        get() = Double.MIN_VALUE
}
