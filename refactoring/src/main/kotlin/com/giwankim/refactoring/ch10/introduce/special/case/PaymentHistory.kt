package com.giwankim.refactoring.ch10.introduce.special.case

open class PaymentHistory(
    val weeksDelinquentInLastYear: Int,
)

class NullPaymentHistory : PaymentHistory(0)
