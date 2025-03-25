package com.giwankim.refactoring.ch10.replace.nested.conditional.with.guard.clauses

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe

class CapitalTest :
    FunSpec({
        context("adjustedCapital") {
            test("capital, interest rate, duration are strictly positive") {
                val instrument =
                    Instrument(capital = 100.0, interestRate = 0.05, duration = 10.0, income = 10.0, adjustmentFactor = 0.1)
                adjustedCapital(instrument) shouldBe 0.1 plusOrMinus 1e-6
            }

            test("capital is zero") {
                val instrument =
                    Instrument(capital = 0.0, interestRate = 0.05, duration = 10.0, income = 10.0, adjustmentFactor = 0.1)
                adjustedCapital(instrument) shouldBe 0.0 plusOrMinus 1e-6
            }

            test("interest rate is zero") {
                val instrument =
                    Instrument(capital = 100.0, interestRate = 0.0, duration = 10.0, income = 10.0, adjustmentFactor = 0.1)
                adjustedCapital(instrument) shouldBe 0.0 plusOrMinus 1e-6
            }

            test("duration is zero") {
                val instrument =
                    Instrument(capital = 100.0, interestRate = 0.05, duration = 0.0, income = 10.0, adjustmentFactor = 0.1)
                adjustedCapital(instrument) shouldBe 0.0 plusOrMinus 1e-6
            }
        }
    })
