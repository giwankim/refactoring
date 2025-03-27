package com.giwankim.refactoring.ch10.introduce.special.case

data class Customer(
    val name: String,
    var billingPlan: BillingPlan,
    val paymentHistory: PaymentHistory,
)

enum class BillingPlan {
    BASIC,
    SPECIAL,
    DELUXE,
}

class PaymentHistory(
    val weeksDelinquentInLastYear: Int,
)
