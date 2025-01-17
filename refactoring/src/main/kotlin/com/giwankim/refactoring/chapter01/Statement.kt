package com.giwankim.refactoring.chapter01

import java.text.NumberFormat
import java.util.Locale

fun statement(
    invoice: Invoice,
    plays: Map<String, Play>,
): String {
    var totalAmount = 0
    var volumeCredits = 0
    val result =
        StringBuilder().apply {
            appendLine("Statement for ${invoice.customer}")
        }
    val formatter = NumberFormat.getCurrencyInstance(Locale.US)

    for (perf in invoice.performances) {
        val play = plays[perf.playID] ?: throw IllegalArgumentException("unknown playID: ${perf.playID}")
        var thisAmount = amountFor(play, perf)

        // add volume credits
        volumeCredits += maxOf(perf.audience - 30, 0)
        // add extra credit for every ten comedy attendees
        if ("comedy" == play.type) {
            volumeCredits += perf.audience / 5
        }

        // print line for this order
        result.appendLine("  ${play.name}: ${formatter.format(thisAmount / 100.0)} (${perf.audience} seats)")
        totalAmount += thisAmount
    }
    result.appendLine("Amount owed is ${formatter.format(totalAmount / 100.0)}")
    result.appendLine("You earned $volumeCredits credits")
    return result.toString()
}

private fun amountFor(
    play: Play,
    perf: Performance,
): Int {
    var thisAmount: Int

    when (play.type) {
        "tragedy" -> {
            thisAmount = 40_000
            if (perf.audience > 30) {
                thisAmount += 1_000 * (perf.audience - 30)
            }
        }

        "comedy" -> {
            thisAmount = 30_000
            if (perf.audience > 20) {
                thisAmount += 10_000 + 500 * (perf.audience - 20)
            }
            thisAmount += 300 * perf.audience
        }

        else -> throw IllegalArgumentException("unknown type: ${play.type}")
    }
    return thisAmount
}
