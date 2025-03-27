package com.giwankim.refactoring.ch10.introduce.special.case.transform

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Site(
    val name: String,
    val location: String,
    val customer: Customer,
)

@Serializable
data class Customer(
    val name: String,
    val billingPlan: BillingPlan,
    val paymentHistory: PaymentHistory,
)

@Serializable
data class PaymentHistory(
    val weeksDelinquentInLastYear: Int,
)

enum class BillingPlan {
    @SerialName("plan-451")
    PLAN_451,
}
