package com.giwankim.refactoring.ch08.move.function

enum class AccountType(
    val isPremium: Boolean,
) {
    PREMIUM(true),
    NORMAL(false), ;

    fun overdraftCharge(daysOverdrawn: Int): Double {
        if (isPremium) {
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
