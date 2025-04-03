package com.giwankim.refactoring.ch11.replace.command.with.function

class ChargeCalculator(
    val customer: Customer,
    val usage: Double,
    val provider: Provider,
) {
    val charge: Double
        get() {
            val baseCharge = customer.baseRate * usage
            return baseCharge + provider.connectionCharge
        }
}
