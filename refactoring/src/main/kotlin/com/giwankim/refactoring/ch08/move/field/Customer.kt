package com.giwankim.refactoring.ch08.move.field

import java.time.LocalDateTime

class Customer(
    val name: String,
    private var _discountRate: Double,
    val contract: CustomerContract = CustomerContract(LocalDateTime.now()),
) {
    var discountRate: Double
        get() = _discountRate
        private set(value) {
            _discountRate = value
        }

    fun becomePreferred() {
        _discountRate += 0.03
        // other nice things
    }

    fun applyDiscount(amount: Double): Double = amount - amount * _discountRate
}
