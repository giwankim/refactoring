package com.giwankim.refactoring.ch10.replace.conditional.with.polymorphism.variation

class Rating(
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

    val captainHistoryRisk: Int
        get() {
            var result = 1
            if (history.size < 5) {
                result += 4
            }
            result += history.count { it.profit < 0 }
            if (voyage.zone == "china" && hasChinaHistory) {
                result -= 2
            }
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
            if (voyage.zone == "china" && hasChinaHistory) {
                result += 3
                if (history.size > 10) {
                    result += 1
                }
                if (voyage.length > 12) {
                    result += 1
                }
                if (voyage.length > 18) {
                    result -= 1
                }
            } else {
                if (history.size > 8) {
                    result += 1
                }
                if (voyage.length > 14) {
                    result -= 1
                }
            }
            return result
        }

    val hasChinaHistory: Boolean
        get() = history.any { it.zone == "china" }
}
