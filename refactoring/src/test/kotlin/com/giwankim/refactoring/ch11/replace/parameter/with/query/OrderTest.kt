package com.giwankim.refactoring.ch11.replace.parameter.with.query

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class OrderTest :
    FunSpec({
        context("finalPrice") {
            test("should apply 5% discount when quantity is 100 or less") {
                val order = Order(quantity = 50, itemPrice = 10.0)
                order.finalPrice() shouldBe 475.0 // 50 * 10 * 0.95
            }

            test("should apply 10% discount when quantity is more than 100") {
                val order = Order(quantity = 150, itemPrice = 10.0)
                order.finalPrice() shouldBe 1350.0 // 150 * 10 * 0.9
            }
        }
    })
