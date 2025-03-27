package com.giwankim.refactoring.ch10.introduce.special.case

open class Customer(
    val name: String,
    var billingPlan: BillingPlan,
    val paymentHistory: PaymentHistory,
) {
    open fun isUnknown(): Boolean = false
}

class UnknownCustomer :
    Customer(
        "unknown",
        BillingPlan.BASIC,
        PaymentHistory(0),
    ) {
    override fun isUnknown(): Boolean = true
}

enum class BillingPlan {
    BASIC,
    SPECIAL,
    DELUXE,
}

class PaymentHistory(
    val weeksDelinquentInLastYear: Int,
)
