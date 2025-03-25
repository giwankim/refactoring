package com.giwankim.refactoring.ch10.introduce.assertion

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CustomerTest :
    FunSpec({
        context("customer") {
            test("constructor discountRate is negative then throw exception") {
                shouldThrow<IllegalArgumentException> { Customer(-0.1) }
            }

            test("set discountRate is negative then throw exception") {
                val customer = Customer(0.0)
                shouldThrow<IllegalArgumentException> { customer.discountRate = -0.1 }
            }

            test("set discountRate to positive value") {
                val customer = Customer(0.2)
                customer.discountRate shouldBe 0.2
            }
        }

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
