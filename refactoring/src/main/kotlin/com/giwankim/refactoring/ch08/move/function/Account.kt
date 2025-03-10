package com.giwankim.refactoring.ch08.move.function

class Account(
    val daysOverdrawn: Int,
    val type: AccountType,
) {
    fun backCharge(): Double {
        var result = 4.5
        if (daysOverdrawn > 0) {
            result += overdraftCharge()
        }
        return result
    }

    fun overdraftCharge(): Double {
        if (this.type.isPremium) {
            val baseCharge = 10.0
            return if (daysOverdrawn <= 7) {
                baseCharge
            } else {
                baseCharge + (daysOverdrawn - 7) * 0.85
            }
        }
        return daysOverdrawn * 1.75
    }
}
