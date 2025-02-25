package com.giwankim.refactoring.chapter06.extract.function.using.local.variables

import com.giwankim.refactoring.chapter06.extract.function.Clock
import com.giwankim.refactoring.chapter06.extract.function.Invoice
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun printOwing(invoice: Invoice) {
    var outstanding = 0

    printBanner()

    // calculate outstanding
    for (o in invoice.orders) {
        outstanding += o.amount
    }

    recordDueDate(invoice)
    printDetails(invoice, outstanding)
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
