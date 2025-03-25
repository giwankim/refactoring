package com.giwankim.refactoring.ch10.introduce.assertion

class Customer(
    val discountRate: Double,
) {
    fun applyDiscount(number: Double): Double = if (discountRate != 0.0) number - (discountRate * number) else number
}
