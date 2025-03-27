package com.giwankim.refactoring.ch10.introduce.special.case

fun client1(site: Site): String {
    val customer = site.customer
    // ... lots of intervening code ...
    val customerName = customer.name
    return customerName
}

fun client2(site: Site): BillingPlan {
    val customer = site.customer
    val plan = if (customer.isUnknown()) BillingPlan.BASIC else customer.billingPlan
    return plan
}

fun client3(
    site: Site,
    newPlan: BillingPlan,
) {
    val customer = site.customer
    if (customer.isUnknown()) {
        return
    }
    customer.billingPlan = newPlan
}

fun client4(site: Site): Int {
    val customer = site.customer
    val weeksDelinquent = if (customer.isUnknown()) 0 else customer.paymentHistory.weeksDelinquentInLastYear
    return weeksDelinquent
}
