package com.giwankim.refactoring.ch11.replace.command.with.function

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ChargeTest :
    FunSpec({
        context("using real objects") {
            test("calculates charge with standard values") {
                val customer = Customer(baseRate = 10.0)
                val provider = Provider(connectionCharge = 5.0)
                val usage = 100.0

                val result = charge(customer, usage, provider)

                result shouldBe 1005.0 // (10.0 * 100.0) + 5.0
            }

            test("calculates charge with zero usage") {
                val customer = Customer(baseRate = 10.0)
                val provider = Provider(connectionCharge = 5.0)
                val usage = 0.0

                val result = charge(customer, usage, provider)

                result shouldBe 5.0 // (10.0 * 0.0) + 5.0
            }

            test("calculates charge with zero base rate") {
                val customer = Customer(baseRate = 0.0)
                val provider = Provider(connectionCharge = 5.0)
                val usage = 100.0

                val result = charge(customer, usage, provider)

                result shouldBe 5.0 // (0.0 * 100.0) + 5.0
            }

            test("calculates charge with zero connection charge") {
                val customer = Customer(baseRate = 10.0)
                val provider = Provider(connectionCharge = 0.0)
                val usage = 100.0

                val result = charge(customer, usage, provider)

                result shouldBe 1000.0 // (10.0 * 100.0) + 0.0
            }
        }
    })
