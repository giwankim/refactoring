package com.giwankim.refactoring.ch08.move.field

import java.time.LocalDateTime

class Customer(
    val name: String,
    var discountRate: Double,
    val contract: CustomerContract = CustomerContract(LocalDateTime.now()),
) {
    fun becomePreferred() {
        discountRate += 0.03
        // other nice things
    }

    fun applyDiscount(amount: Double): Double = amount - amount * discountRate
}
