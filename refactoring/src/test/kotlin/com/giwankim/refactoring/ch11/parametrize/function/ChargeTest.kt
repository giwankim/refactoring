package com.giwankim.refactoring.ch11.parametrize.function

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.javamoney.moneta.Money

class ChargeTest :
    FunSpec({

        context("baseCharge") {
            test("should return 0 for negative usage") {
                baseCharge(-10.0) shouldBe Money.of(0.0, "USD")
            }

            test("should return 0 for zero usage") {
                baseCharge(0.0) shouldBe Money.of(0.0, "USD")
            }

            test("should calculate correctly for bottom band only") {
                // 50 * 0.03 = 1.50
                baseCharge(50.0) shouldBe Money.of(1.50, "USD")
            }

            test("should calculate correctly at bottom band boundary") {
                // 100 * 0.03 = 3.00
                baseCharge(100.0) shouldBe Money.of(3.00, "USD")
            }

            test("should calculate correctly for middle band") {
                // (100 * 0.03) + (50 * 0.05) = 3.00 + 2.50 = 5.50
                baseCharge(150.0) shouldBe Money.of(5.50, "USD")
            }

            test("should calculate correctly at middle band boundary") {
                // (100 * 0.03) + (100 * 0.05) = 3.00 + 5.00 = 8.00
                baseCharge(200.0) shouldBe Money.of(8.00, "USD")
            }

            test("should calculate correctly for top band") {
                // (100 * 0.03) + (100 * 0.05) + (100 * 0.07) = 3.00 + 5.00 + 7.00 = 15.00
                baseCharge(300.0) shouldBe Money.of(15.00, "USD")
            }
        }
    })
