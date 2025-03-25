package com.giwankim.refactoring.ch10.replace.nested.conditional.with.guard.clauses

fun adjustedCapital(instrument: Instrument): Double {
    if (instrument.capital <= 0.0 || instrument.interestRate <= 0.0 || instrument.duration <= 0.0) {
        return 0.0
    }
    return (instrument.income / instrument.duration) * instrument.adjustmentFactor
}
