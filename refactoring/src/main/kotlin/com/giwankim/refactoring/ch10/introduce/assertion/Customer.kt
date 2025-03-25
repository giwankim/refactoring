package com.giwankim.refactoring.ch10.introduce.assertion

class Customer(
    private var _discountRate: Double,
) {
    var discountRate: Double
        get() = _discountRate
        set(value) {
            requirePositive(value)
            _discountRate = value
        }

    init {
        requirePositive(_discountRate)
    }

    fun applyDiscount(number: Double): Double = if (discountRate != 0.0) number - (discountRate * number) else number

    private fun requirePositive(value: Double) {
        require(value >= 0.0) { "discountRate should be positive" }
    }
}
