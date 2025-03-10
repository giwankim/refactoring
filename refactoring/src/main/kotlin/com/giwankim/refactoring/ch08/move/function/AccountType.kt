package com.giwankim.refactoring.ch08.move.function

enum class AccountType(
    val isPremium: Boolean,
) {
    PREMIUM(true),
    NORMAL(false),
}
