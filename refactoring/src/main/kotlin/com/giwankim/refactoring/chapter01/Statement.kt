package com.giwankim.refactoring.chapter01

import java.text.NumberFormat
import java.util.Locale

fun statement(
    invoice: Invoice,
    plays: Map<String, Play>,
): String {
    fun playFor(perf: Performance): Play = plays[perf.playID] ?: throw IllegalArgumentException("unknown playID: ${perf.playID}")

    fun enrichPerformance(aPerformance: Performance): EnrichedPerformance =
        EnrichedPerformance(aPerformance.playID, aPerformance.audience, playFor(aPerformance))

    val statementData = StatementData(invoice.customer, invoice.performances.map(::enrichPerformance))
    return renderPlainText(statementData, plays)
}

fun renderPlainText(
    data: StatementData,
    plays: Map<String, Play>,
): String {
    fun amountFor(aPerformance: EnrichedPerformance): Int {
        var result: Int
        when (aPerformance.play.type) {
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

            else -> throw IllegalArgumentException("unknown type: ${aPerformance.play.type}")
        }
        return result
    }

    fun volumeCreditsFor(aPerformance: EnrichedPerformance): Int {
        var result = 0
        result += maxOf(aPerformance.audience - 30, 0)
        if ("comedy" == aPerformance.play.type) {
            result += aPerformance.audience / 5
        }
        return result
    }

    fun usd(aNumber: Int): String = NumberFormat.getCurrencyInstance(Locale.US).format(aNumber / 100.0)

    fun totalVolumeCredits(): Int {
        var result = 0
        for (perf in data.performances) {
            result += volumeCreditsFor(perf)
        }
        return result
    }

    fun totalAmount(): Int {
        var result = 0
        for (perf in data.performances) {
            result += amountFor(perf)
        }
        return result
    }

    return buildString {
        appendLine("Statement for ${data.customer}")
        data.performances.forEach { perf ->
            appendLine("  ${perf.play.name}: ${usd(amountFor(perf))} (${perf.audience} seats)")
        }
        appendLine("Amount owed is ${usd(totalAmount())}")
        appendLine("You earned ${totalVolumeCredits()} credits")
    }
}

data class StatementData(
    val customer: String,
    val performances: List<EnrichedPerformance>,
)

data class EnrichedPerformance(
    val playID: String,
    val audience: Int,
    val play: Play,
)
