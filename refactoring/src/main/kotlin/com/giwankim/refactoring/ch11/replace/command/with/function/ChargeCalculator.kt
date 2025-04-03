package com.giwankim.refactoring.ch11.replace.command.with.function

class ChargeCalculator(
    val customer: Customer,
    val usage: Double,
    val provider: Provider,
) {
    fun charge(
        customer: Customer,
        usage: Double,
        provider: Provider,
    ): Double {
        val baseCharge = customer.baseRate * usage
        return baseCharge + provider.connectionCharge
    }
}
