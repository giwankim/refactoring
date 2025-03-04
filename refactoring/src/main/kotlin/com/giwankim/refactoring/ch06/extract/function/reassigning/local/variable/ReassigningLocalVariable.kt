package com.giwankim.refactoring.ch06.extract.function.reassigning.local.variable

import com.giwankim.refactoring.ch06.extract.function.Clock
import com.giwankim.refactoring.ch06.extract.function.Invoice
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun printOwing(invoice: Invoice) {
    printBanner()
    val outstanding = calculateOutstanding(invoice)
    recordDueDate(invoice)
    printDetails(invoice, outstanding)
}

private fun calculateOutstanding(invoice: Invoice): Int {
    var result = 0
    for (o in invoice.orders) {
        result += o.amount
    }
    return result
}

private fun recordDueDate(invoice: Invoice) {
    val today = Clock.today()
    invoice.dueDate = today.plusDays(30)
}

private fun printDetails(
    invoice: Invoice,
    outstanding: Int,
) {
    val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.KOREA)
    println("name: ${invoice.customer}")
    println("amount: $outstanding")
    println("due: ${formatter.format(invoice.dueDate)}")
}

private fun printBanner() {
    println("***********************")
    println("**** Customer Owes ****")
    println("***********************")
}
