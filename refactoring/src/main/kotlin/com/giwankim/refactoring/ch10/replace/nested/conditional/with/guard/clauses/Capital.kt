package com.giwankim.refactoring.ch10.replace.nested.conditional.with.guard.clauses

fun adjustedCapital(instrument: Instrument): Double {
    var result = 0.0
    if (instrument.capital > 0.0) {
        if (instrument.interestRate > 0.0 && instrument.duration > 0.0) {
            result = (instrument.income / instrument.duration) * instrument.adjustmentFactor
        }
    }
    return result
}
