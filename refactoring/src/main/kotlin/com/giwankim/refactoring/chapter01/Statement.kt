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
        var result = 0
        result += maxOf(aPerformance.audience - 30, 0)
        if ("comedy" == playFor(aPerformance).type) {
            result += aPerformance.audience / 5
        }
        return result
    }

    fun usd(aNumber: Int): String = NumberFormat.getCurrencyInstance(Locale.US).format(aNumber / 100.0)

    fun totalVolumeCredits(): Int {
        var volumeCredits = 0
        for (perf in invoice.performances) {
            volumeCredits += volumeCreditsFor(perf)
        }
        return volumeCredits
    }

    fun totalAmount(): Int {
        var totalAmount = 0
        for (perf in invoice.performances) {
            totalAmount += amountFor(perf)
        }
        return totalAmount
    }

    val result = StringBuilder().apply { appendLine("Statement for ${invoice.customer}") }
    for (perf in invoice.performances) {
        result.appendLine("  ${playFor(perf).name}: ${usd(amountFor(perf))} (${perf.audience} seats)")
    }

    result.appendLine("Amount owed is ${usd(totalAmount())}")
    result.appendLine("You earned ${totalVolumeCredits()} credits")
    return result.toString()
}
