package com.giwankim.refactoring.chapter01

import kotlinx.serialization.Serializable
import java.text.NumberFormat
import java.util.Locale

fun statement(
    invoice: Invoice,
    plays: Map<String, Play>,
): String = createStatementData(invoice, plays).renderPlainText()

fun StatementData.renderPlainText(): String =
    buildString {
        appendLine("Statement for $customer")
        performances.forEach { perf ->
            appendLine("  ${perf.play.name}: ${usd(perf.amount)} (${perf.audience} seats)")
        }
        appendLine("Amount owed is ${usd(totalAmount)}")
        appendLine("You earned $totalVolumeCredits credits")
    }

fun htmlStatement(
    invoice: Invoice,
    plays: Map<String, Play>,
): String = createStatementData(invoice, plays).renderHtml()

fun StatementData.renderHtml(): String =
    buildString {
        appendLine("<h1>Statement for $customer</h1>")
        appendLine("<table>")
        appendLine("<tr><th>play</th><th>seats</th><th>cost</th></tr>")
        performances.forEach { perf ->
            append("  <tr><td>${perf.play.name}</td><td>${perf.audience}</td>")
            appendLine("<td>${usd(perf.amount)}</td></tr>")
        }
        appendLine("</table>")
        appendLine("<p>Amount owed is <em>${usd(totalAmount)}</em></p>")
        appendLine("<p>You earned <em>$totalVolumeCredits</em> credits</p>")
    }

fun usd(number: Int): String = NumberFormat.getCurrencyInstance(Locale.US).format(number / 100.0)

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
