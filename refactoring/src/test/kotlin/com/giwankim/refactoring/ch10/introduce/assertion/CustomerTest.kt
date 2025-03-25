package com.giwankim.refactoring.ch10.introduce.assertion

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CustomerTest :
    FunSpec({
        context("applyDiscount") {
            test("discountRate is 0.0 then no discount") {
                val customer = Customer(0.0)
                customer.applyDiscount(100.0) shouldBe 100.0
            }

            test("discountRate is positive then apply discount") {
                val customer = Customer(0.1)
                customer.applyDiscount(100.0) shouldBe 90.0
            }
        }
    })
