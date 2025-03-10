package com.giwankim.refactoring.ch06.extract.function.no.variable.out.of.scope

import com.giwankim.refactoring.ch06.extract.function.Clock
import com.giwankim.refactoring.ch06.extract.function.Invoice
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

    // record due data
    val today = Clock.today()
    invoice.dueDate = today.plusDays(30)

    fun printDetails() {
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.KOREA)
        println("name: ${invoice.customer}")
        println("amount: $outstanding")
        println("due: ${formatter.format(invoice.dueDate)}")
    }

    printDetails()
}

private fun printBanner() {
    println("***********************")
    println("**** Customer Owes ****")
    println("***********************")
}
