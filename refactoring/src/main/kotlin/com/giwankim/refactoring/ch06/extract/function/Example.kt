package com.giwankim.refactoring.ch06.extract.function

import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun printOwing(invoice: Invoice) {
    var outstanding = 0

    println("***********************")
    println("**** Customer Owes ****")
    println("***********************")

    // calculate outstanding
    for (o in invoice.orders) {
        outstanding += o.amount
    }

    // record due data
    val today = Clock.today()
    invoice.dueDate = today.plusDays(30)

    // print details
    val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.KOREA)
    println("name: ${invoice.customer}")
    println("amount: $outstanding")
    println("due: ${formatter.format(invoice.dueDate)}")
}

fun main() {
    val invoice = Invoice(emptyList(), Clock.today(), "john")
    printOwing(invoice)
}
