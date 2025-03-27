package com.giwankim.refactoring.ch10.introduce.special.case

fun client1(site: Site): String {
    val customer = site.customer
    // ... lots of intervening code ...
    val customerName = customer?.name ?: "occupant"
    return customerName
}

fun client2(site: Site): BillingPlan {
    val customer = site.customer
    val plan = customer?.billingPlan ?: BillingPlan.BASIC
    return plan
}

fun client3(
    site: Site,
    newPlan: BillingPlan,
): Customer? {
    val customer = site.customer
    customer?.billingPlan = newPlan
    return customer
}

fun client4(site: Site): Int {
    val customer = site.customer
    val weeksDelinquent = customer?.paymentHistory?.weeksDelinquentInLastYear ?: 0
    return weeksDelinquent
}
