package com.giwankim.refactoring.ch11.replace.command.with.function

fun charge(
    customer: Customer,
    usage: Double,
    provider: Provider,
): Double {
    val baseCharge = customer.baseRate * usage
    return baseCharge + provider.connectionCharge
}
