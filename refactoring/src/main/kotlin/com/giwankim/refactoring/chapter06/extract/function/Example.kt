package com.giwankim.refactoring.chapter06.extract.function

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
    println("name: ${invoice.customer}")
    println("amount: $outstanding")
    println("due: ${invoice.dueDate}")
}
