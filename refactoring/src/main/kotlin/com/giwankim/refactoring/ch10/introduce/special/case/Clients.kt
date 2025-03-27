package com.giwankim.refactoring.ch10.introduce.special.case

fun client1(site: Site): String {
    val customer = site.customer
    // ... lots of intervening code ...
    return customer.name
}

fun client2(site: Site): BillingPlan {
    val customer = site.customer
    return customer.billingPlan
}

fun client3(
    site: Site,
    newPlan: BillingPlan,
) {
    val customer = site.customer
    customer.billingPlan = newPlan
}

fun client4(site: Site): Int {
    val customer = site.customer
    return customer.paymentHistory.weeksDelinquentInLastYear
}
