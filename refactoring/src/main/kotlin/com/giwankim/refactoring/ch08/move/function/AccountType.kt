package com.giwankim.refactoring.ch08.move.function

enum class AccountType(
    val isPremium: Boolean,
) {
    PREMIUM(true),
    NORMAL(false), ;

    fun overdraftCharge(account: Account): Double {
        if (isPremium) {
            val baseCharge = 10.0
            return if (account.daysOverdrawn <= 7) {
                baseCharge
            } else {
                baseCharge + (account.daysOverdrawn - 7) * 0.85
            }
        }
        return account.daysOverdrawn * 1.75
    }
}
