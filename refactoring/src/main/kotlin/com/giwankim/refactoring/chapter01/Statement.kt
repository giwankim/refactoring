package com.giwankim.refactoring.chapter01

import java.text.NumberFormat
import java.util.Locale

fun statement(
    invoice: Invoice,
    plays: Map<String, Play>,
): String = renderPlainText(createStatementData(invoice, plays), plays)

private fun createStatementData(
    invoice: Invoice,
    plays: Map<String, Play>,
): StatementData {
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

    fun playFor(perf: Performance): Play = plays[perf.playID] ?: throw IllegalArgumentException("unknown playID: ${perf.playID}")

    fun enrichPerformance(aPerformance: Performance): EnrichedPerformance =
        EnrichedPerformance(aPerformance.playID, aPerformance.audience, playFor(aPerformance)).apply {
            amount = amountFor(this)
            volumeCredits = volumeCreditsFor(this)
        }

    fun totalVolumeCredits(data: StatementData): Int = data.performances.sumOf { it.volumeCredits }

    fun totalAmount(data: StatementData): Int = data.performances.sumOf { it.amount }

    val statementData =
        StatementData(invoice.customer, invoice.performances.map(::enrichPerformance)).apply {
            totalAmount = totalAmount(this)
            totalVolumeCredits = totalVolumeCredits(this)
        }
    return statementData
}

fun renderPlainText(
    data: StatementData,
    plays: Map<String, Play>,
): String {
    fun usd(aNumber: Int): String = NumberFormat.getCurrencyInstance(Locale.US).format(aNumber / 100.0)

    return buildString {
        appendLine("Statement for ${data.customer}")
        data.performances.forEach { perf ->
            appendLine("  ${perf.play.name}: ${usd(perf.amount)} (${perf.audience} seats)")
        }
        appendLine("Amount owed is ${usd(data.totalAmount)}")
        appendLine("You earned ${data.totalVolumeCredits} credits")
    }
}

data class StatementData(
    val customer: String,
    val performances: List<EnrichedPerformance>,
    var totalAmount: Int = 0,
    var totalVolumeCredits: Int = 0,
)

data class EnrichedPerformance(
    val playID: String,
    val audience: Int,
    val play: Play,
    var amount: Int = 0,
    var volumeCredits: Int = 0,
)
