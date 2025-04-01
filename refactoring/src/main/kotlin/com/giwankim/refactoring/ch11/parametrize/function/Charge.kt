package com.giwankim.refactoring.ch11.parametrize.function

import org.javamoney.moneta.Money
import kotlin.math.min

fun baseCharge(usage: Double): Money {
    if (usage < 0.0) {
        return usd(0.0)
    }
    val amount = bottomBand(usage) * 0.03 + middleBand(usage) * 0.05 + topBand(usage) * 0.07
    return usd(amount)
}

private fun bottomBand(usage: Double): Double = min(usage, 100.0)

private fun middleBand(usage: Double): Double =
    if (usage > 100.0) {
        min(usage, 200.0) - 100.0
    } else {
        0.0
    }

private fun topBand(usage: Double): Double =
    if (usage > 200.0) {
        usage - 200.0
    } else {
        0.0
    }

private fun usd(amount: Double): Money = Money.of(amount, "USD")
