package com.giwankim.refactoring.ch11.replace.command.with.function

fun charge(
    customer: Customer,
    usage: Double,
    provider: Provider,
): Double = ChargeCalculator(customer, usage, provider).charge
