package com.giwankim.refactoring.ch11.parametrize.function

import org.javamoney.moneta.Money
import kotlin.math.min

fun baseCharge(usage: Double): Money {
    if (usage < 0.0) {
        return usd(0.0)
    }
    val amount =
        withinBand(usage, 0.0, 100.0) * 0.03 + withinBand(usage, 100.0, 200.0) * 0.05 + withinBand(
            usage,
            200.0,
            Double.MAX_VALUE,
        ) * 0.07
    return usd(amount)
}

private fun withinBand(
    usage: Double,
    bottom: Double,
    top: Double,
): Double =
    if (usage > bottom) {
        min(usage, top) - bottom
    } else {
        0.0
    }

private fun usd(amount: Double): Money = Money.of(amount, "USD")
