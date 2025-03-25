package com.giwankim.refactoring.ch10.consolidate.conditional.expression

import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class DisabilityCalculatorTest :
    FunSpec({
        context("DisabilityCalculator") {
            test("seniority < 2 should get 0") {
                val employee = Employee(seniority = 1, monthsDisabled = 1, isPartTime = false)
                disabilityAmount(employee) shouldBe 0
            }

            test("seniority = 2, 3 should get 1") {
                val seniorEmployees =
                    listOf(
                        Employee(seniority = 2, monthsDisabled = 1, isPartTime = false),
                        Employee(seniority = 3, monthsDisabled = 1, isPartTime = false),
                    )
                seniorEmployees.forAll { disabilityAmount(it) shouldBe 1 }
            }

            test("monthsDisabled > 12 should get 0") {
                val employee = Employee(seniority = 2, monthsDisabled = 13, isPartTime = false)
                disabilityAmount(employee) shouldBe 0
            }

            test("monthsDisabled = 11, 12 should get 1") {
                val disabledEmployees =
                    listOf(
                        Employee(seniority = 2, monthsDisabled = 11, isPartTime = false),
                        Employee(seniority = 2, monthsDisabled = 12, isPartTime = false),
                    )
                disabledEmployees.forAll { disabilityAmount(it) shouldBe 1 }
            }

            test("isPartTime = true should get 0") {
                val employee = Employee(seniority = 2, monthsDisabled = 1, isPartTime = true)
                disabilityAmount(employee) shouldBe 0
            }

            test("isPartTime = false should get 1") {
                val employee = Employee(seniority = 2, monthsDisabled = 1, isPartTime = false)
                disabilityAmount(employee) shouldBe 1
            }
        }
    })
