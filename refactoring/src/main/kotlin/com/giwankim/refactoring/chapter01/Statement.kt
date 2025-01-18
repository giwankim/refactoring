package com.giwankim.refactoring.chapter01

import kotlinx.serialization.Serializable
import java.text.NumberFormat
import java.util.Locale

fun statement(
    invoice: Invoice,
    plays: Map<String, Play>,
): String = renderPlainText(createStatementData(invoice, plays))

fun renderPlainText(data: StatementData): String =
    buildString {
        appendLine("Statement for ${data.customer}")
        data.performances.forEach { perf ->
            appendLine("  ${perf.play.name}: ${usd(perf.amount)} (${perf.audience} seats)")
        }
        appendLine("Amount owed is ${usd(data.totalAmount)}")
        appendLine("You earned ${data.totalVolumeCredits} credits")
    }

fun htmlStatement(
    invoice: Invoice,
    plays: Map<String, Play>,
): String = renderHtml(createStatementData(invoice, plays))

fun renderHtml(data: StatementData): String =
    buildString {
        appendLine("<h1>Statement for ${data.customer}</h1>")
        appendLine("<table>")
        appendLine("<tr><th>play</th><th>seats</th><th>cost</th></tr>")
        data.performances.forEach { perf ->
            append("  <tr><td>${perf.play.name}</td><td>${perf.audience}</td>")
            appendLine("<td>${usd(perf.amount)}</td></tr>")
        }
        appendLine("</table>")
        appendLine("<p>Amount owed is <em>${usd(data.totalAmount)}</em></p>")
        appendLine("<p>You earned <em>${data.totalVolumeCredits}</em> credits</p>")
    }

fun usd(aNumber: Int): String = NumberFormat.getCurrencyInstance(Locale.US).format(aNumber / 100.0)

@Serializable
data class Play(
    val name: String,
    val type: String,
)

@Serializable
data class Performance(
    val playID: String,
    val audience: Int,
)

@Serializable
data class Invoice(
    val customer: String,
    val performances: List<Performance>,
)
