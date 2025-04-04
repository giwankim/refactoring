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

    override val voyageProfitFactor: Int
        get() = super.voyageProfitFactor + 3

    override val voyageLengthFactor: Int
        get() {
            var result = 0
            if (voyage.length > 12) {
                result += 1
            }
            if (voyage.length > 18) {
                result -= 1
            }
            return result
        }

    override val historyLengthFactor: Int
        get() = if (history.size > 10) 1 else 0
}
