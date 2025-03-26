package com.giwankim.refactoring.ch10.replace.conditional.with.polymorphism.variation

fun rating(
    voyage: Voyage,
    history: History,
): String = Rating(voyage, history).value

fun voyageRisk(voyage: Voyage): Int {
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

fun captainHistoryRisk(
    voyage: Voyage,
    history: History,
): Int {
    var result = 1
    if (history.size < 5) {
        result += 4
    }
    result += history.count { it.profit < 0 }
    if (voyage.zone == "china" && hasChina(history)) {
        result -= 2
    }
    return result.coerceAtLeast(0)
}

fun hasChina(history: History): Boolean = history.any { it.zone == "china" }

fun voyageProfitFactor(
    voyage: Voyage,
    history: History,
): Int {
    var result = 2
    if (voyage.zone == "china") {
        result += 1
    }
    if (voyage.zone == "east-indies") {
        result += 1
    }
    if (voyage.zone == "china" && hasChina(history)) {
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
