package com.giwankim.refactoring.ch11.replace.command.with.function

class ChargeCalculator(
    val customer: Customer,
    val usage: Double,
    val provider: Provider,
) {
    val baseCharge: Double
        get() = customer.baseRate * usage
    val charge: Double
        get() {
            val baseCharge = this.baseCharge
            return baseCharge + provider.connectionCharge
        }
}
