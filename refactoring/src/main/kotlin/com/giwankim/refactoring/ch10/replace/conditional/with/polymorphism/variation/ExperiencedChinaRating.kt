package com.giwankim.refactoring.ch10.replace.conditional.with.polymorphism.variation

class ExperiencedChinaRating(
    voyage: Voyage,
    history: History,
) : Rating(voyage, history) {
    override val captainHistoryRisk: Int
        get() {
            val result = super.captainHistoryRisk - 2
            return result.coerceAtLeast(0)
        }
}
