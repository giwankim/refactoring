package com.giwankim.refactoring.ch08.move.field

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe

class CustomerTest :
    FunSpec({
        test("apply discount") {
            val customer = Customer("Martin Fowler", 0.05)
            val discountedAmount = customer.applyDiscount(100.0)
            discountedAmount shouldBe 95.0 plusOrMinus 1e-3
        }

        test("apply discount for preferred customer") {
            val customer = Customer("Martin Fowler", 0.05).apply { becomePreferred() }
            val discountedAmount = customer.applyDiscount(100.0)
            discountedAmount shouldBe 92.0 plusOrMinus 1e-3
        }
    })
