package com.giwankim.refactoring.chapter01

import java.text.NumberFormat
import java.util.Locale

fun statement(
    invoice: Invoice,
    plays: Map<String, Play>,
): String {
    fun playFor(perf: Performance): Play = plays[perf.playID] ?: throw IllegalArgumentException("unknown playID: ${perf.playID}")

    fun amountFor(aPerformance: Performance): Int {
        var result: Int
        when (playFor(aPerformance).type) {
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

            else -> throw IllegalArgumentException("unknown type: ${playFor(aPerformance).type}")
        }
        return result
    }

    fun volumeCreditsFor(aPerformance: Performance): Int {
        var volumeCredits = 0
        volumeCredits += maxOf(aPerformance.audience - 30, 0)
        if ("comedy" == playFor(aPerformance).type) {
            volumeCredits += aPerformance.audience / 5
        }
        return volumeCredits
    }

    fun format(aNumber: Double): String = NumberFormat.getCurrencyInstance(Locale.US).format(aNumber)

    var totalAmount = 0
    var volumeCredits = 0
    val result = StringBuilder().apply { appendLine("Statement for ${invoice.customer}") }
    for (perf in invoice.performances) {
        volumeCredits += volumeCreditsFor(perf)

        // print line for this order
        result.appendLine("  ${playFor(perf).name}: ${format(amountFor(perf) / 100.0)} (${perf.audience} seats)")
        totalAmount += amountFor(perf)
    }
    result.appendLine("Amount owed is ${format(totalAmount / 100.0)}")
    result.appendLine("You earned $volumeCredits credits")
    return result.toString()
}
