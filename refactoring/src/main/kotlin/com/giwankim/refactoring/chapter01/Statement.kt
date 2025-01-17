package com.giwankim.refactoring.chapter01

import java.text.NumberFormat
import java.util.Locale

fun statement(
    invoice: Invoice,
    plays: Map<String, Play>,
): String {
    fun playFor(perf: Performance): Play = plays[perf.playID] ?: throw IllegalArgumentException("unknown playID: ${perf.playID}")

    var totalAmount = 0
    var volumeCredits = 0
    val result =
        StringBuilder().apply {
            appendLine("Statement for ${invoice.customer}")
        }
    val formatter = NumberFormat.getCurrencyInstance(Locale.US)

    for (perf in invoice.performances) {
        var thisAmount = amountFor(perf, playFor(perf))

        // add volume credits
        volumeCredits += maxOf(perf.audience - 30, 0)
        // add extra credit for every ten comedy attendees
        if ("comedy" == playFor(perf).type) {
            volumeCredits += perf.audience / 5
        }

        // print line for this order
        result.appendLine("  ${playFor(perf).name}: ${formatter.format(thisAmount / 100.0)} (${perf.audience} seats)")
        totalAmount += thisAmount
    }
    result.appendLine("Amount owed is ${formatter.format(totalAmount / 100.0)}")
    result.appendLine("You earned $volumeCredits credits")
    return result.toString()
}

private fun amountFor(
    aPerformance: Performance,
    play: Play,
): Int {
    var result: Int

    when (play.type) {
        "tragedy" -> {
            result = 40_000
            if (aPerformance.audience > 30) {
                result += 1_000 * (aPerformance.audience - 30)
            }
        }

        "comedy" -> {
            result = 30_000
            if (aPerformance.audience > 20) {
                result += 10_000 + 500 * (aPerformance.audience - 20)
            }
            result += 300 * aPerformance.audience
        }

        else -> throw IllegalArgumentException("unknown type: ${play.type}")
    }
    return result
}
