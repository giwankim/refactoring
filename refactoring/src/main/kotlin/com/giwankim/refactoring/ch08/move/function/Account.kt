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

    fun overdraftCharge(): Double = type.overdraftCharge(daysOverdrawn)
}
