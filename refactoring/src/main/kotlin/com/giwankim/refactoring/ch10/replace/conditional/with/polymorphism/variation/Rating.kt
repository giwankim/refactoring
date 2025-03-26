package com.giwankim.refactoring.ch10.replace.conditional.with.polymorphism.variation

open class Rating(
    val voyage: Voyage,
    val history: History,
) {
    val value: String
        get() {
            val vpf = voyageProfitFactor
            val vr = voyageRisk
            val chr = captainHistoryRisk
            return if (vpf * 3 > (vr + chr * 2)) {
                "A"
            } else {
                "B"
            }
        }
    val voyageRisk: Int
        get() {
            var result = 1
            if (voyage.length > 4) {
                result += 2
            }
            if (voyage.length > 8) {
                result += voyage.length - 8
            }
            if (voyage.zone in listOf("china", "east-indies")) {
                result += 4
            }
            return result.coerceAtLeast(0)
        }

    open val captainHistoryRisk: Int
        get() {
            var result = 1
            if (history.size < 5) {
                result += 4
            }
            result += history.count { it.profit < 0 }
            return result.coerceAtLeast(0)
        }

    val voyageProfitFactor: Int
        get() {
            var result = 2
            if (voyage.zone == "china") {
                result += 1
            }
            if (voyage.zone == "east-indies") {
                result += 1
            }
            result += historyLengthFactor
            result += voyageLengthFactor
            return result
        }

    open val voyageLengthFactor: Int
        get() = if (voyage.length > 14) -1 else 0

    open val historyLengthFactor: Int
        get() = if (history.size > 8) 1 else 0
}
