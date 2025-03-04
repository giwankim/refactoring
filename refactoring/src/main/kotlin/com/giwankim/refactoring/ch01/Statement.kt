package com.giwankim.refactoring.ch01

import kotlinx.serialization.Serializable
import java.text.NumberFormat
import java.util.Locale

fun statement(
    invoice: Invoice,
    plays: Map<String, Play>,
): String =
    StatementData
        .create(invoice, plays)
        .renderPlainText()

fun StatementData.renderPlainText(): String =
    buildString {
        appendLine("Statement for $customer")
        performances.forEach { perf ->
            appendLine("  ${perf.play.name}: ${perf.amount.usd()} (${perf.audience} seats)")
        }
        appendLine("Amount owed is ${totalAmount.usd()}")
        appendLine("You earned $totalVolumeCredits credits")
    }

fun htmlStatement(
    invoice: Invoice,
    plays: Map<String, Play>,
): String =
    StatementData
        .create(invoice, plays)
        .renderHtml()

fun StatementData.renderHtml(): String =
    buildString {
        appendLine("<h1>Statement for $customer</h1>")
        appendLine("<table>")
        appendLine("<tr><th>play</th><th>seats</th><th>cost</th></tr>")
        performances.forEach { perf ->
            append("  <tr><td>${perf.play.name}</td><td>${perf.audience}</td>")
            appendLine("<td>${perf.amount.usd()}</td></tr>")
        }
        appendLine("</table>")
        appendLine("<p>Amount owed is <em>${totalAmount.usd()}</em></p>")
        appendLine("<p>You earned <em>$totalVolumeCredits</em> credits</p>")
    }

fun Int.usd(): String = NumberFormat.getCurrencyInstance(Locale.US).format(this / 100.0)

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
