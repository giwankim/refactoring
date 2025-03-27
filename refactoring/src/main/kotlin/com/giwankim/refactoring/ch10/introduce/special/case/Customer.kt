package com.giwankim.refactoring.ch10.introduce.special.case

open class Customer(
    val name: String,
    _billingPlan: BillingPlan,
    val paymentHistory: PaymentHistory,
) {
    open var billingPlan: BillingPlan = _billingPlan
        set(value) {
            field = value
        }

    open fun isUnknown(): Boolean = false
}

class UnknownCustomer :
    Customer(
        "occupant",
        BillingPlan.BASIC,
        PaymentHistory(0),
    ) {
    override var billingPlan: BillingPlan
        get() = BillingPlan.BASIC
        set(value) {
            // do nothing
        }

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
