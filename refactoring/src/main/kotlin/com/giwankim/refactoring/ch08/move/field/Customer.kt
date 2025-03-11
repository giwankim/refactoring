package com.giwankim.refactoring.ch08.move.field

import java.time.LocalDateTime

class Customer(
    val name: String,
    private var _discountRate: Double,
    val contract: CustomerContract = CustomerContract(LocalDateTime.now(), _discountRate),
) {
    var discountRate: Double
        get() = contract.discountRate
        private set(value) {
            contract.discountRate = value
        }

    fun becomePreferred() {
        contract.discountRate += 0.03
        // other nice things
    }

    fun applyDiscount(amount: Double): Double = amount - amount * contract.discountRate
}
