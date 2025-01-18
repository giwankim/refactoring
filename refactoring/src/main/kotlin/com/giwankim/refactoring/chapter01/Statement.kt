package com.giwankim.refactoring.chapter01

import java.text.NumberFormat
import java.util.Locale

fun statement(
    invoice: Invoice,
    plays: Map<String, Play>,
): String = renderPlainText(createStatementData(invoice, plays))

fun renderPlainText(data: StatementData): String {
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
